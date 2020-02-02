package com.amoon.doctorlist.util

import android.content.Context
import android.preference.PreferenceManager
import com.amoon.doctorlist.data.model.DoctorDetails
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Storage {

    object DataProviderManager {

        fun checkList(context: Context, list: DoctorDetails) {
            var recentTemp = arrayListOf<DoctorDetails>()
            var recentList = getArrayList(context)

            recentTemp.add(list)
            for (i in 0 until recentList?.size!!) {
                var rList = recentList?.get(i)
                if (recentTemp.size == 3)
                    break
                else if (!list.id.contentEquals(rList?.id.toString())) {
                    recentTemp.add(rList!!)
                }
            }
            recentList = recentTemp
            saveArrayList(context, recentList, "RecentList")
        }


        private fun saveArrayList(context: Context, list: List<DoctorDetails>, key: String?) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = prefs.edit()
            val gson = Gson()
            val json = gson.toJson(list)
            editor.putString(key, json)
            editor.apply()
        }


        fun getArrayList(context: Context): List<DoctorDetails> {
            lateinit var seenResult: List<DoctorDetails>
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val gson = Gson()
            val json = prefs.getString("RecentList", null)
            val type: Type = object : TypeToken<Collection<DoctorDetails>>() {}.getType()

            seenResult = if (json != null)
                gson.fromJson(json, type)
            else
                emptyList()
            return seenResult
        }
    }

}