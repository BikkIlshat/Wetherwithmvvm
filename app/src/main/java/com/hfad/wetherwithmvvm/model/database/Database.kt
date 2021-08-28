package com.hfad.wetherwithmvvm.model.database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hfad.wetherwithmvvm.framework.App

@androidx.room.Database(
    entities = [
        HistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        private const val DB_NAME = "add_database.db"
        val db: Database by lazy {
            Room.databaseBuilder(
                App.appContext,
                Database::class.java,
                DB_NAME
            ).build()
        }
    }
}