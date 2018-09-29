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

    private fun readApiDataFile() {
        val file = File("$fileDir\\\\api_data")
        if (file.exists()) {
            apiMap.putAll(readFile("$fileDir\\\\api_data") as HashMap<String, Api>)
        }
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

    fun addApi(name: String) {
        if (apiMap.containsKey(name)) {
            //TODO 弹窗提示重复
            return
        }
        apiMap[name] = Api(name, HashMap(), "")
        refreshDataFile()
    }


    fun deleteApi(name: String) {
        if (apiMap.containsKey(name)) {
            apiMap.remove(name)
            refreshDataFile()
        }
    }
    fun addDataTemplate(name: String) {
        if (currentApi.dataTemplates.containsKey(name)) {
            //TODO 弹窗提示重复
            return
        }
        currentApi.dataTemplates[name] = ""
        apiMap[currentApi.name] = currentApi
        refreshDataFile()
    }

    fun deleteDataTemplate(name: String) {
        if (currentApi.dataTemplates.containsKey(name)) {
            currentApi.dataTemplates.remove(name)
            apiMap[currentApi.name] = currentApi
            refreshDataFile()
        }
    }
    fun getApiDataByName(apiName: String): Api {
        return apiMap[apiName] as Api
    }

    fun setCurrentTemplate(data : String) {
        currentApi.currentDataTemplate = data
        apiMap[currentApi.name] = currentApi
        refreshDataFile()
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
        lateinit var fileIn: FileInputStream
        lateinit var objectIn: ObjectInputStream
        lateinit var data: Any
        try {
            fileIn = FileInputStream(path)
            objectIn = ObjectInputStream(fileIn)
            data = objectIn.readObject()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fileIn.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                objectIn.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return data
    }
}