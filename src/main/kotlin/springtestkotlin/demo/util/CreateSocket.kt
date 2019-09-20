package springtestkotlin.demo.util

import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

fun createSocketFactory(protocols: List<String>) =
        SSLContext.getInstance(protocols[0]).apply {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
                override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
            })
            init(null, trustAllCerts, SecureRandom())
        }.socketFactory

fun urlConnect(connectUrl: HttpsURLConnection): String {
    val baos = ByteArrayOutputStream()
    return try {
        connectUrl.apply {
            sslSocketFactory = createSocketFactory(listOf("TLSv1.2"))
            hostnameVerifier = HostnameVerifier { _, _ -> true }
            readTimeout = 5_000
        }.inputStream.use {
            it.copyTo(baos)
        }
        baos.toString()
    } catch (e: Exception) {
        throw Exception("Exception while push the notification  $e.message")
    }
}