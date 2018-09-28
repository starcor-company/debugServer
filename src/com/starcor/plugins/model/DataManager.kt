package com.starcor.plugins.model

import java.io.*

/**
 * author：heng.zhang
 * date：2018/9/19
 * description：
 */
class DataManager {
    companion object {
        private val apiMap  = HashMap<String,Api>()
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
        fun refreshApi() {
            writeFile("$fileDir\\\\api_data", apiMap)
        }

        fun addApi(name : String, function : String) {
            if (apiMap.containsKey(name)) {
                //TODO 弹窗提示重复
                return
            }
            apiMap[name] = Api(name, function, HashMap())
            refreshApi()
        }

        private fun writeFile(path : String, data : Any) {
            lateinit var fileOut : FileOutputStream
            lateinit var objectOut : ObjectOutputStream
            try {
                fileOut = FileOutputStream(path)
                objectOut = ObjectOutputStream(fileOut)
                objectOut.writeObject(data)
            } catch (e : Exception){
                e.printStackTrace()
            } finally {
                try{
                    fileOut.close()
                    objectOut.close()
                }catch (e : Exception) {
                    e.printStackTrace()
                }
            }
        }

        private fun readFile(path : String) : Any {
            lateinit var fileOut : FileInputStream
            lateinit var objectOut : ObjectInputStream
            lateinit var data : Any
            try {
                fileOut = FileInputStream(path)
                objectOut = ObjectInputStream(fileOut)
                data =  objectOut.readObject()
            } catch (e : Exception){
                e.printStackTrace()
            } finally {
                try{
                    fileOut.close()
                    objectOut.close()
                }catch (e : Exception) {
                    e.printStackTrace()
                }
            }
            return data
        }
    }
}