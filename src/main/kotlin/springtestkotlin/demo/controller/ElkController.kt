package springtestkotlin.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

@RestController
@RequestMapping("/elk")
class ElkController {

    @GetMapping("/sampleData")
    fun elkSample(): String {
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

    @GetMapping("/sampleData/{ip}")
    fun elkSampleGetbyIp(@PathVariable ip: String): String {
        val connectUrl = URL("http://127.0.0.1:9200/kibana_sample_data_logs/_search?pretty").openConnection() as HttpURLConnection
        val elkPostString = "{\"query\":{\"bool\":{\"filter\":[{\"term\":{\"ip\":\"$ip\"}},{\"range\":{\"timestamp\":{\"gte\":\"now-10d/d\",\"lt\":\"now\"}}}]}}}"
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