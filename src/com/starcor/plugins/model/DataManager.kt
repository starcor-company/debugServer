package com.starcor.plugins.model

import java.io.*

/**
 * author：heng.zhang
 * date：2018/9/19
 * description：
 */
object DataManager {
    lateinit var currentApi : Api
    private val apiMap = HashMap<String, Api>()
    private val fileDir = System.getProperty("user.home") + "\\.debugServer"

    fun init() {
        val file = File(fileDir)
        if (!file.exists()) {
            file.mkdir()
        }
        readApiDataFile()
    }

    fun readApiDataFile() {
        apiMap.putAll(readFile("$fileDir\\\\api_data") as HashMap<String, Api>)
    }


    fun getApiNameSet(): Set<String> {
        return apiMap.keys
    }

    fun saveTemplateData(key : String, data : String) {
        currentApi.currentDataTemplate = data
        currentApi.dataTemplates[key] = data
        apiMap[currentApi.name] = currentApi
        refreshDataFile()
    }

    private fun refreshDataFile() {
        writeFile("$fileDir\\\\api_data", apiMap)
    }
    fun addApi(name: String, function: String) {
        if (apiMap.containsKey(name)) {
            //TODO 弹窗提示重复
            return
        }
        apiMap[name] = Api(name, function, HashMap(), "")
        var aaa = apiMap[name] as Api
        aaa.dataTemplates["模版1"] = "模版1的数据"
        aaa.dataTemplates["模版2"] = "模版2的数据"
        aaa.dataTemplates["模版3"] = "模版3的数据"
        aaa.dataTemplates["模版4"] = "模版4的数据"
        refreshDataFile()
    }

    fun getApiDataByName(apiName: String): Api {
        return apiMap[apiName] as Api
    }

    private fun writeFile(path: String, data: Any) {
        lateinit var fileOut: FileOutputStream
        lateinit var objectOut: ObjectOutputStream
        try {
            fileOut = FileOutputStream(path)
            objectOut = ObjectOutputStream(fileOut)
            objectOut.writeObject(data)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fileOut.close()
                objectOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun readFile(path: String): Any {
        lateinit var fileOut: FileInputStream
        lateinit var objectOut: ObjectInputStream
        lateinit var data: Any
        try {
            fileOut = FileInputStream(path)
            objectOut = ObjectInputStream(fileOut)
            data = objectOut.readObject()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fileOut.close()
                objectOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return data
    }
}