data class SdkUser (
    var id: String = "",
    var name: String = ""
)

class Usage {
    fun onGetUser() {
        saveUser(sdkUser)
        uiSetUser(sdkUser)
    }
    
    fun saveUser(sdkUser: SdkUser) {
        SharedPrefsHelper.save(sdkUser)
    }
}

class SharedPrefsHelper {
    companion object save(sdkUser: SdkUser) {
        val id = sdkUser.id
        val name = sdkUser.name
        
    }
}