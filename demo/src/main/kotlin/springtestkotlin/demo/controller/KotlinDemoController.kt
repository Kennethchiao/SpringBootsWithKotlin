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

    @GetMapping("/")
    fun home(): String {
        return "Hi this is index page"
    }

    @Value("\${openSourceWeatherUrl}")
    lateinit var weatherUrl: String

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

    @GetMapping("/elk/1")
    fun elkTest1(): String {
        val connectUrl = URL("http://127.0.0.1:9200/kibana_sample_data_logs/_search?pretty").openConnection() as HttpURLConnection
        val elkPostString = "{\"query\":{\"bool\":{\"filter\":[{\"term\":{\"ip\":\"191.105.163.245\"}},{\"range\":{\"timestamp\":{\"gte\":\"now-10d/d\",\"lt\":\"now\"}}}]}}}"
        val postData: ByteArray = elkPostString.toByteArray(StandardCharsets.UTF_8)

        return try {
            connectUrl.apply {
                requestMethod = "POST"
                connectTimeout = 90000
                doOutput = true
                doInput = true
                setRequestProperty("charset", "utf-8")
                setRequestProperty("Content-lenght", postData.size.toString())
                setRequestProperty("Content-Type", "application/json")
            }

            val outputStream: DataOutputStream = DataOutputStream(connectUrl.outputStream)
            outputStream.write(postData)
            outputStream.flush()

            if (connectUrl.responseCode != HttpURLConnection.HTTP_OK || connectUrl.responseCode != HttpURLConnection.HTTP_CREATED) {
                val reader: BufferedReader = BufferedReader(InputStreamReader(connectUrl.inputStream))
                val output: String = reader.readText()
                output
            } else {
                "Response =  ${connectUrl.responseCode}"
            }

        } catch (e: Exception) {
            throw Exception("Exception while push the notification  $e.message")
        }
    }

}