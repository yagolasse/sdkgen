
import android.location.Location
import android.util.Base64
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import android.view.WindowManager
import android.content.pm.PackageManager
import android.os.Build
import org.json.JSONException
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.provider.Settings
import okhttp3.*

open class API {
    private lateinit var context: Context
    
    interface Calls {
       fun getFooString(var1: String, flag: Int? = null, callback: (error: Error?, value: String?) -> Unit) 
       fun getFooBase64(var1: String, flag: Int? = null, callback: (error: Error?, value: String?) -> Unit) 
       fun getFooCnpj(var1: String, flag: Int? = null, callback: (error: Error?, cnpj: String?) -> Unit) 
       fun getFooCep(var1: String, flag: Int? = null, callback: (error: Error?, cep: String?) -> Unit) 
       fun getFooCpf(var1: String, flag: Int? = null, callback: (error: Error?, cpf: String?) -> Unit) 
       fun getFooDate(var1: Calendar, flag: Int? = null, callback: (error: Error?, datetime: Calendar?) -> Unit) 
       fun getFooEmail(var1: String, flag: Int? = null, callback: (error: Error?, email: String?) -> Unit) 
       fun getFooFloat(var1: Float, flag: Int? = null, callback: (error: Error?, value: Float?) -> Unit) 
       fun getFooInt(var1: Int, flag: Int? = null, callback: (error: Error?, value: Int?) -> Unit) 
       fun getFooUInt(var1: Int, flag: Int? = null, callback: (error: Error?, value: Int?) -> Unit) 
       fun getFooBytes(var1: ByteArray, flag: Int? = null, callback: (error: Error?, value: ByteArray?) -> Unit) 
       fun getFooDateTime(var1: Calendar, flag: Int? = null, callback: (error: Error?, datetime: Calendar?) -> Unit) 
       fun getFooMoney(var1: Int, flag: Int? = null, callback: (error: Error?, money: Int?) -> Unit) 
       fun getFooPhone(var1: String, flag: Int? = null, callback: (error: Error?, phone: String?) -> Unit) 
       fun getFooUrl(var1: String, flag: Int? = null, callback: (error: Error?, url: String?) -> Unit) 
       fun getFooUuid(var1: String, flag: Int? = null, callback: (error: Error?, uuid: String?) -> Unit) 
       fun getFooXml(var1: String, flag: Int? = null, callback: (error: Error?, xml: String?) -> Unit) 
       fun ping(flag: Int? = null, callback: (error: Error?, value: String?) -> Unit) 
       fun setPushToken(token: String, flag: Int? = null, callback: (error: Error?, result: Boolean?) -> Unit) 
    }

	companion object {
      var instance: API? = null
      fun init(context: Context, useStaging: Boolean) {
            API.useStaging = useStaging
            instance = API().apply { this.context = context }
      }
      
      const val BASE_URL = ""
      var useStaging = false
      private val hexArray = "0123456789abcdef".toCharArray()
      
      var connectionPool = ConnectionPool(100, 45, TimeUnit.SECONDS)
      var client = OkHttpClient.Builder()
            .connectionPool(connectionPool)
            .dispatcher(Dispatcher().apply { maxRequests = 200 ; maxRequestsPerHost = 200 })
            .connectTimeout(45, TimeUnit.SECONDS)
            .build()
      
      var calls = object: Calls { 
         override fun getFooString(var1: String, flag: Int?, callback: (error: Error?, value: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooString", bodyArgs, callback) 
          }
         override fun getFooBase64(var1: String, flag: Int?, callback: (error: Error?, value: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooBase64", bodyArgs, callback) 
          }
         override fun getFooCnpj(var1: String, flag: Int?, callback: (error: Error?, cnpj: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1.replace(Regex("/[^0-9]/g"), "").padStart(14, 0.toChar()))
                                } 
                               makeRequest("getFooCnpj", bodyArgs, callback) 
          }
         override fun getFooCep(var1: String, flag: Int?, callback: (error: Error?, cep: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1.replace(Regex("/[^0-9]/g"), "").padStart(8, 0.toChar()))
                                } 
                               makeRequest("getFooCep", bodyArgs, callback) 
          }
         override fun getFooCpf(var1: String, flag: Int?, callback: (error: Error?, cpf: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1.replace(Regex("/[^0-9]/g"), "").padStart(11, 0.toChar()))
                                } 
                               makeRequest("getFooCpf", bodyArgs, callback) 
          }
         override fun getFooDate(var1: Calendar, flag: Int?, callback: (error: Error?, datetime: Calendar?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(var1))
                                } 
                               makeRequest("getFooDate", bodyArgs, callback) 
          }
         override fun getFooEmail(var1: String, flag: Int?, callback: (error: Error?, email: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooEmail", bodyArgs, callback) 
          }
         override fun getFooFloat(var1: Float, flag: Int?, callback: (error: Error?, value: Float?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooFloat", bodyArgs, callback) 
          }
         override fun getFooInt(var1: Int, flag: Int?, callback: (error: Error?, value: Int?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooInt", bodyArgs, callback) 
          }
         override fun getFooUInt(var1: Int, flag: Int?, callback: (error: Error?, value: Int?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooUInt", bodyArgs, callback) 
          }
         override fun getFooBytes(var1: ByteArray, flag: Int?, callback: (error: Error?, value: ByteArray?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", Base64.encodeToString(var1, Base64.DEFAULT))
                                } 
                               makeRequest("getFooBytes", bodyArgs, callback) 
          }
         override fun getFooDateTime(var1: Calendar, flag: Int?, callback: (error: Error?, datetime: Calendar?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(var1))
                                } 
                               makeRequest("getFooDateTime", bodyArgs, callback) 
          }
         override fun getFooMoney(var1: Int, flag: Int?, callback: (error: Error?, money: Int?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooMoney", bodyArgs, callback) 
          }
         override fun getFooPhone(var1: String, flag: Int?, callback: (error: Error?, phone: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooPhone", bodyArgs, callback) 
          }
         override fun getFooUrl(var1: String, flag: Int?, callback: (error: Error?, url: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooUrl", bodyArgs, callback) 
          }
         override fun getFooUuid(var1: String, flag: Int?, callback: (error: Error?, uuid: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooUuid", bodyArgs, callback) 
          }
         override fun getFooXml(var1: String, flag: Int?, callback: (error: Error?, xml: String?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("var1", var1)
                                } 
                               makeRequest("getFooXml", bodyArgs, callback) 
          }
         override fun ping(flag: Int?, callback: (error: Error?, value: String?) -> Unit) {
         }
         override fun setPushToken(token: String, flag: Int?, callback: (error: Error?, result: Boolean?) -> Unit) {
              var bodyArgs = JSONObject().apply { 
                                    put("token", token)
                                } 
                               makeRequest("setPushToken", bodyArgs, callback) 
          }
      } 

      class Error {
            var type: ErrorType? = null
            var message: String? = null
      }

      fun randomBytesHex(len: Int): String {
          val bytes = ByteArray(len)
          Random().nextBytes(bytes)
          return bytesToHex(bytes)
      }

      private fun bytesToHex(bytes: ByteArray): String {
          val hexChars = CharArray(bytes.size * 2)
          for (j in bytes.indices) {
              val v = bytes[j].toInt() and 0xFF
              hexChars[j * 2] = hexArray[v ushr 4 ]
              hexChars[j * 2 + 1] = hexArray[v and 0x0F]
          }
          return String(hexChars)
      }

      @SuppressLint("HardwareIds")
      @Throws(JSONException::class)
      fun device(): JSONObject =
          JSONObject().apply {
              put("type", "android")
              put("fingerprint", "" + Settings.Secure.getString(instance!!.context.contentResolver, Settings.Secure.ANDROID_ID))
              put("platform", object : JSONObject() {
                  init {
                      put("version", Build.VERSION.RELEASE)
                      put("sdkVersion", Build.VERSION.SDK_INT)
                      put("brand", Build.BRAND)
                      put("model", Build.MODEL)
                  }
              })
              try {
                  put("version", instance!!.context.packageManager.getPackageInfo(instance!!.context.packageName, 0).versionName)
              } catch (e: PackageManager.NameNotFoundException) {
                  put("version", "unknown")
              }

              put("language", language())
              put("screen", object : JSONObject() {
                  init {
                      val manager = instance!!.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                      val display = manager.defaultDisplay
                      val size = Point()
                      display.getSize(size)
                      put("width", size.x)
                      put("height", size.y)
                  }
              })
              val pref = instance!!.context.getSharedPreferences("api", Context.MODE_PRIVATE)
              if (pref.contains("deviceId")) put("id", pref.getString("deviceId", null))
          }


        private fun language(): String {
            val loc = Locale.getDefault()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                return loc.toLanguageTag()
            }

            val sep = '-'
            var language = loc.language
            var region = loc.country
            var variant = loc.variant

            if (language == "no" && region == "NO" && variant == "NY") {
                language = "nn"
                region = "NO"
                variant = ""
            }

            if (language.isEmpty() || !language.matches("\\[a-zA-Z]{2,8}".toRegex())) {
                language = "und"
            } else if (language == "iw") {
                language = "he"
            } else if (language == "in") {
                language = "id"
            } else if (language == "ji") {
                language = "yi"
            }

            if (!region.matches("\\[a-zA-Z]{2}|\\[0-9]{3}".toRegex())) {
                region = ""
            }

            if (!variant.matches("\\[a-zA-Z0-9]{5,8}|\\[0-9]\\[a-zA-Z0-9]{3}".toRegex())) {
                variant = ""
            }

            val bcp47Tag = StringBuilder(language)
            if (!region.isEmpty()) {
                bcp47Tag.append(sep).append(region)
            }
            if (!variant.isEmpty()) {
                bcp47Tag.append(sep).append(variant)
            }

            return bcp47Tag.toString()
        }

        private fun <T> makeRequest(functionName: String, bodyArgs: JSONObject, callback: (error: Error?, result: T?) -> Unit, timeoutSeconds: Int = 15) {
            try {

                val body = JSONObject().apply {
                    put("id", randomBytesHex(8))
                    put("device", device())
                    put("name", functionName)
                    put("args", bodyArgs)
                    put("staging", API.useStaging)
                }

                val request = Request.Builder()
                        .url("https://$BASE_URL${if (useStaging) "-staging" else ""}")
                        .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body.toString()))
                        .build()
                client.newCall(request)
            } catch (e: JSONException) {
                e.printStackTrace()
                callback(Error(), null)
            }
        }
    }
}