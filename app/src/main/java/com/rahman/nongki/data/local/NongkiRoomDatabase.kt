package com.rahman.nongki.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Nongki::class], version = 1)
abstract class NongkiRoomDatabase : RoomDatabase() {

    abstract fun nongkiDao(): NongkiDao

    companion object {
        @Volatile
        private var INSTANCE: NongkiRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): NongkiRoomDatabase {
            if (INSTANCE == null) {
                synchronized(NongkiRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NongkiRoomDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as NongkiRoomDatabase
        }
    }
}