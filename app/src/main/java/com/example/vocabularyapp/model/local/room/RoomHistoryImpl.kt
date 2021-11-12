package com.example.vocabularyapp.model.local.room

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.Meanings
import com.example.vocabularyapp.model.Translation

class RoomHistoryImpl(private val db: Database): IDataSourceHistory<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        val listOfDataModel = mutableListOf<DataModel>()
        val listOfWords = db.wordDao.getAll()
        for (i in listOfWords.indices) {
            listOfDataModel.add(
                DataModel(
                    (listOfWords[i].word),
                    mutableListOf(Meanings(Translation(listOfWords[i].translation), null))
                )
            )
        }
        return listOfDataModel
    }

    override suspend fun insertData(data: List<DataModel>?) {
        if (data != null) {
//            for (i in data.indices) {
                db.wordDao.insertAll(Word(null, data[0].text, data[0].meanings?.get(0)?.translation?.translation))
//            }
        }
    }
}