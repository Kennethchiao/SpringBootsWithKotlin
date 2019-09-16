package springtestkotlin.demo.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import springtestkotlin.demo.util.createSocketFactory
import springtestkotlin.demo.util.urlConnect
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection


@RestController
class DemoController {

    /* api value  */

    @Value("\${openSourceWeatherUrl}")
    lateinit var weatherUrl: String


    /* api method */

    @GetMapping("/")
    fun home(): String {
        return "Hi this is index page"
    }

    @GetMapping("/openSource/weather")
    fun openSource1(): String {

        var connectUrl = URL(weatherUrl).openConnection() as HttpsURLConnection
        return urlConnect(connectUrl)
    }

    @GetMapping("/openSource/married")
    fun openSourceMarried(): String {
        var connectUrl = URL("https://od.moi.gov.tw/api/v1/rest/datastore/301000000A-001446-001").openConnection() as HttpsURLConnection
        return urlConnect(connectUrl)
    }

}