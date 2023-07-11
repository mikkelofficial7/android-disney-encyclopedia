package com.ewide.test.mikkel.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.ewide.test.mikkel.base.dao.BaseDao
import com.ewide.test.mikkel.model.local.ListCharacter


@Dao
interface DataDao : BaseDao<ListCharacter> {
    @RawQuery
    fun getAllFavoriteCharacter(query: SupportSQLiteQuery): List<ListCharacter?>?

    @Query("select * from table_item_character where gameID = :id")
    fun getFavoriteItemById(id: String): ListCharacter?

    @Query("delete from table_item_character where gameID = :id")
    fun deleteFavoriteItemById(id: String)
}

fun DataDao.queryAllFavoriteCharacter(isAscending: Boolean = false): List<ListCharacter?>? {
    val sortOrder = if (isAscending) "ASC" else "DESC"
    val query = String.format("SELECT * FROM table_item_character ORDER BY id %s", sortOrder)
    val sqlQuery: SupportSQLiteQuery = SimpleSQLiteQuery(query)
    return this.getAllFavoriteCharacter(sqlQuery)
}