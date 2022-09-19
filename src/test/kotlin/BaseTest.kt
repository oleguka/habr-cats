import Specs.requestSpec
import Specs.responseSpec
import config.Config
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.config.LogConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.filter.log.LogDetail.ALL
import io.restassured.filter.log.LogDetail.BODY
import io.restassured.http.ContentType.JSON
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTest {

    @BeforeAll
    fun setup() {

        RestAssured.filters(AllureRestAssured())

        val logConfig = LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(ALL)
        val config = RestAssuredConfig.config().logConfig(logConfig)

        requestSpec = RequestSpecBuilder()
            .setBaseUri(Config.url)
            .setPort(Config.port)
            .addHeader("Accept", "application/json")
            .setContentType(JSON)
            .setConfig(config)
            .build()

        responseSpec = ResponseSpecBuilder()
            .log(BODY)
            .build()

    }

    @AfterAll
    fun tearDown() {
        RestAssured.reset()
    }

}