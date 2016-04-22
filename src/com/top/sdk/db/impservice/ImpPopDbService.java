package com.top.sdk.db.impservice;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.top.sdk.db.DBConstant;
import com.top.sdk.db.DBHelper;
import com.top.sdk.db.service.PopDbService;
import com.top.sdk.entity.PopData;

public class ImpPopDbService implements PopDbService {
	private DBHelper openHelper;
	private SQLiteDatabase dataBase;

	public ImpPopDbService(Context context) {
		openHelper = DBHelper.getInstance(context);
		dataBase = openHelper.getWritableDatabase();
	}

	@Override
	public List<PopData> getPopDataList() {
		String sql = "select * from " + DBConstant.TABLE_NAME_POPDATA;
		List<PopData> list = null;
		Cursor cursor = null;
		try {
			cursor = dataBase.rawQuery(sql, null);
			list = new ArrayList<PopData>();
			if (cursor != null && cursor.moveToFirst()) {
				do {
					PopData data = new PopData();
					data.setId(cursor.getInt(cursor.getColumnIndex("popId")));
					data.setPopType(cursor.getInt(cursor
							.getColumnIndex("popType")));
					data.setPopUrl(cursor.getString(cursor
							.getColumnIndex("popUrl")));
					data.setImgUrl(cursor.getString(cursor
							.getColumnIndex("imgUrl")));
					data.setChannelName(cursor.getString(cursor
							.getColumnIndex("channelName")));
					data.setPackageName(cursor.getString(cursor
							.getColumnIndex("packageName")));
					data.setShowCount(cursor.getInt(cursor
							.getColumnIndex("showCount")));
					data.setShowRate(cursor.getInt(cursor
							.getColumnIndex("showRate")));
					data.setVersion(cursor.getString(cursor
							.getColumnIndex("version")));
					data.setCreateTime(cursor.getString(cursor
							.getColumnIndex("createTime")));
					list.add(data);
				} while (cursor.moveToNext());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (cursor != null) {
				cursor.close();
			}
		}

		return list;
	}

	@Override
	public PopData getPopDataFromId(int id) {
		String sql = "select * from " + DBConstant.TABLE_NAME_POPDATA
				+ "where popId=?";
		Cursor cursor = null;
		try {
			cursor = dataBase.rawQuery(sql, new String[] { "" + id });
			PopData data = new PopData();
			if (cursor != null && cursor.moveToFirst()) {
				do {
					data.setPopType(cursor.getInt(cursor
							.getColumnIndex("popType")));
					data.setPopUrl(cursor.getString(cursor
							.getColumnIndex("popUrl")));
					data.setImgUrl(cursor.getString(cursor
							.getColumnIndex("imgUrl")));
					data.setChannelName(cursor.getString(cursor
							.getColumnIndex("channelName")));
					data.setPackageName(cursor.getString(cursor
							.getColumnIndex("packageName")));
					data.setShowCount(cursor.getInt(cursor
							.getColumnIndex("showCount")));
					data.setShowRate(cursor.getInt(cursor
							.getColumnIndex("showRate")));
					data.setVersion(cursor.getString(cursor
							.getColumnIndex("version")));
					data.setCreateTime(cursor.getString(cursor
							.getColumnIndex("createTime")));

				} while (cursor.moveToNext());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (cursor != null) {
				cursor.close();
			}
		}

		return null;
	}

	@Override
	public boolean deletePopData(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllPopData() {
		dataBase.execSQL("");
		return false;
	}

	@Override
	public boolean insertPopData(PopData popData) {
		ContentValues values = new ContentValues();
		values.put("popType", popData.getPopType());
		values.put("popUrl", popData.getPopUrl());
		values.put("imgUrl", popData.getImgUrl());
		values.put("channelName", popData.getChannelName());
		values.put("packageName", popData.getPackageName());
		values.put("showCount", popData.getShowCount());
		values.put("showRate", popData.getShowRate());
		values.put("version", popData.getVersion() + "");
		long result = dataBase.insert(DBConstant.TABLE_NAME_POPDATA, null,
				values);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePopData(PopData popData) {
		// TODO Auto-generated method stub
		return false;
	}

}
