package com.example.vocabularyapp.model.local.room

import com.example.vocabularyapp.contracts.DataSource
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.model.Meanings
import com.example.vocabularyapp.model.Translation

class RoomHistoryImpl(private val db: Database): IDataSourceHistory<List<DataModel>> {
    override suspend fun getData(): List<DataModel> {
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
                db.wordDao.insertAll(Word(null, data[0].text, data[0].meanings?.get(0)?.translation?.translation))
        }
    }

    override suspend fun getDataByQuery(query: String): DataModel {
        var dataModel: DataModel? = null
        val word = db.wordDao.getByWord(query)
        if (word != null) {
            dataModel = DataModel(word.word, listOf(Meanings(Translation(word.translation), null)))
        } else {
            dataModel = DataModel("notFound", listOf(Meanings(Translation(""), null)))
        }
        return dataModel
    }
}