package rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import rafting.masti.rishikesh.holiday.adventure.bailiwick.com.adventure.Modal.CartListModel;

public class DBOperation {
    final static String PREFS_NAME = "MyPrefsFile";


    // Insert Booth in table
    public static boolean insertBooth(Context c, String bid, String booth_name, String consistency_name) {

        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);

        Cursor cur = null;

        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();

            cur = database.rawQuery("select * from " + Database_Utils.Booth_table, null);


            int len = cur.getCount();
            System.out.println("lenght " + len);

            ContentValues newValues = new ContentValues();
            // Assign values for each row.
            Log.e("bid", bid);
            Log.e("booth_name", booth_name);
            Log.e("consistency_name", consistency_name);

            newValues.put(Database_Utils.Booth_BID, bid);
            newValues.put(Database_Utils.Booth_name, booth_name);
            newValues.put(Database_Utils.Booth_consistency, consistency_name);

            // Insert the row into your table
            database.insert(Database_Utils.Booth_table, null, newValues);
            ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();


            //	database.execSQL(sql);
            database.close();
            DatabaseHelper.closedatabase();
            return true;
        } catch (Exception e) {
            return false;
        }


    }


    ///////////////////// /*helping APi*/////////////////////
    ///////////////////// /*helping APi*/////////////////////


    ////////////////// Prince test///////////////////
    public static int getRaftCount(Context c, String Cart_id) {
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        Cursor cur = null;
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();
            {
                //cur = database.rawQuery("select Quotes from LoveQuotes where isFav =1", null);
                // cur = database.rawQuery("select * from " + Database_Utils.Cart_table, null);
                cur = database.rawQuery("select * from " + Database_Utils.Cart_table + " where cart_id ='"
                        + Cart_id + "'", null);

            }
            int len=0;
             len = cur.getCount();
            System.out.println("lenght " + len);
            if (len > 0) {
                SharedPreferences pageNumPref = c.getSharedPreferences(
                        PREFS_NAME, 0);
                pageNumPref.edit().putInt("dbsize", len).commit();

            } else {
                cur.close();
                DatabaseHelper.closedatabase();
            }
            cur.moveToNext();
            cur.close();
            DatabaseHelper.closedatabase();
            return len;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Insert Rafting
    public static boolean insertRaftingCart(Context activity, String id, String tittle, String Str_service,
                                            String Str_check_in, String Str_check_out, String Str_no_nights, String Str_adult,
                                            String Str_child, String Str_starting_point, String Str_booking_date,
                                            String Str__qty, String price, String total, String imageurl, String isRafting,
                                            String str_name, String str_mobile, String str_message, String str_email) {

        DatabaseHelper dbhelperShopCart = new DatabaseHelper(activity);

        Cursor cur = null;

        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();

            cur = database.rawQuery("select * from " + Database_Utils.Cart_table, null);


            int len = cur.getCount();
            System.out.println("lenght " + len);

            ContentValues newValues = new ContentValues();
            // Assign values for each row.
            Log.e("vendorID", id);
            Log.e("price", price);
            Log.e("amount", total);
            Log.e("amount", total);


            newValues.put(Database_Utils.cart_id, id);
            newValues.put(Database_Utils.cart_tittle, tittle);
            newValues.put(Database_Utils.cart_service, Str_service);
            newValues.put(Database_Utils.cart_check_in, Str_check_in);
            newValues.put(Database_Utils.cart_check_out, Str_check_out);
            newValues.put(Database_Utils.cart_no_nights, Str_no_nights);
            newValues.put(Database_Utils.cart_adult, Str_adult);
            newValues.put(Database_Utils.cart_child, Str_child);

            newValues.put(Database_Utils.cart_starting_point, Str_starting_point);
            newValues.put(Database_Utils.cart_booking_date, Str_booking_date);
            newValues.put(Database_Utils.cart__qty, Str__qty);
            newValues.put(Database_Utils.cart_price, price);
            newValues.put(Database_Utils.cart_total, total);
            newValues.put(Database_Utils.cart_image_url, imageurl);
            newValues.put(Database_Utils.cart_isRafting, isRafting);
            newValues.put(Database_Utils.cart_name, str_name);
            newValues.put(Database_Utils.cart_mobile, str_mobile);
            newValues.put(Database_Utils.cart_message, str_message);
            newValues.put(Database_Utils.cart_email, str_email);

            // Insert the row into your table
            database.insert(Database_Utils.Cart_table, null, newValues);
            ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();


            //	database.execSQL(sql);
            database.close();
            DatabaseHelper.closedatabase();
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    // Insert Camping
    public static boolean insertCampaningCart(Context activity, String id, String tittle, String Str_service,
                                              String Str_check_in, String Str_check_out, String Str_no_nights, String Str_adult,
                                              String Str_child, String Str_starting_point, String Str_booking_date,
                                              String Str__qty, String price, String total, String imageurl, String isRafting, String str_room_type_id,
                                              String str_name, String str_mobile, String str_message, String str_email) {

        DatabaseHelper dbhelperShopCart = new DatabaseHelper(activity);

        Cursor cur = null;

        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();

            cur = database.rawQuery("select * from " + Database_Utils.Cart_table, null);


            int len = cur.getCount();
            System.out.println("lenght " + len);

            ContentValues newValues = new ContentValues();
            // Assign values for each row.
            Log.e("vendorID", id);
            Log.e("price", price);
            Log.e("amount", total);
            Log.e("amount", total);


            newValues.put(Database_Utils.cart_id, id);
            newValues.put(Database_Utils.cart_tittle, tittle);
            newValues.put(Database_Utils.cart_service, Str_service);
            newValues.put(Database_Utils.cart_check_in, Str_check_in);
            newValues.put(Database_Utils.cart_check_out, Str_check_out);
            newValues.put(Database_Utils.cart_no_nights, Str_no_nights);

            newValues.put(Database_Utils.cart_adult, Str_adult);
            newValues.put(Database_Utils.cart_child, Str_child);

            newValues.put(Database_Utils.cart_starting_point, Str_starting_point);
            newValues.put(Database_Utils.cart_booking_date, Str_booking_date);
            newValues.put(Database_Utils.cart__qty, Str__qty);
            newValues.put(Database_Utils.cart_price, price);
            newValues.put(Database_Utils.cart_total, total);
            newValues.put(Database_Utils.cart_image_url, imageurl);
            newValues.put(Database_Utils.cart_isRafting, isRafting);

            newValues.put(Database_Utils.cart_room_type, str_room_type_id);
            newValues.put(Database_Utils.cart_name, str_name);
            newValues.put(Database_Utils.cart_mobile, str_mobile);
            newValues.put(Database_Utils.cart_message, str_message);
            newValues.put(Database_Utils.cart_email, str_email);

            // Insert the row into your table
            database.insert(Database_Utils.Cart_table, null, newValues);
            ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();


            //	database.execSQL(sql);
            database.close();
            DatabaseHelper.closedatabase();
            return true;
        } catch (Exception e) {
            return false;
        }


    }


    // Get Cart List
    public static ArrayList<CartListModel> getCartList(Context c) {
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        Cursor cur = null;
        ArrayList<CartListModel> cartListModals;
        cartListModals = new ArrayList<>();
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();
            {
                //cur = database.rawQuery("select Quotes from LoveQuotes where isFav =1", null);
                cur = database.rawQuery("select * from " + Database_Utils.Cart_table, null);

            }
            int len = cur.getCount();
            System.out.println("lenght " + len);
            if (len > 0) {
                SharedPreferences pageNumPref = c.getSharedPreferences(
                        PREFS_NAME, 0);
                pageNumPref.edit().putInt("dbsize", len).commit();
            } else {
                cur.close();
                DatabaseHelper.closedatabase();
            }
            cur.moveToNext();
            for (int i = 0; i < len; i++) {
                cartListModals.add(new CartListModel(cur.getString(cur.getColumnIndex(Database_Utils.cart_id)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_tittle)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_service)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_check_in)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_check_out)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_no_nights)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_adult)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_child)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_starting_point)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_booking_date)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart__qty)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_price)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_total)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_image_url)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_isRafting)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_room_type)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_name)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_mobile)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_message)),
                        cur.getString(cur.getColumnIndex(Database_Utils.cart_email))
                ));


                Log.e("booth detail List", "" + cur.getString(cur.getColumnIndex(Database_Utils.cart_id)));
                cur.moveToNext();

                //	System.out.println(data[i]);

            }
            cur.close();
            DatabaseHelper.closedatabase();
            return cartListModals;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Delete Cart Itemt
    public static boolean deleteCartItem(Context c, String Cart_id) {

        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();

            //   String sql = "DELETE from " + Database_Utils.Man_Ki_Baat_table + " where" + Database_Utils.Entry_no + "='" + ENTRY_no + "' ";
            database.execSQL("delete from " + Database_Utils.Cart_table + " where cart_id='" + Cart_id + "'");
            ///   database.execSQL(sql);
            database.close();
            DatabaseHelper.closedatabase();
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    // delete Cart Table
    public static boolean deleteAll_Cart(Context c) {
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();

            String sql = "delete from " + Database_Utils.Cart_table;
            database.execSQL(sql);
            database.close();
            DatabaseHelper.closedatabase();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    ////////////////////////Prince Test Close//////////////////
    // mine
    public static String[] getAlluserquote(Context c) {
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        Cursor cur = null;
        String data[];// = new String[3];
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();
            {
                cur = database.rawQuery("select Quotes from LoveQuotes where isFav = 1", null);
            }
            int len = cur.getCount();
            System.out.println("lenght  " + len);
            if (len > 0) {
                SharedPreferences pageNumPref = c.getSharedPreferences(
                        PREFS_NAME, 0);
                pageNumPref.edit().putInt("dbsize", len).commit();
            } else {
                cur.close();
                DatabaseHelper.closedatabase();
            }
            data = new String[len];
            cur.moveToNext();
            for (int i = 0; i < len; i++) {
                data[i] = cur.getString(cur.getColumnIndex("Quotes"));
                cur.moveToNext();

                //	System.out.println("Data is our" +data[i]);

            }
            cur.close();
            DatabaseHelper.closedatabase();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] getAllUserQuote(Context c) {
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        Cursor cur = null;
        String data[];// = new String[3];
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();
            {
                cur = database.rawQuery(
                        "select Quotes from LoveQuotes where isFav=1", null);
            }
            int len = cur.getCount();
            System.out.println("lenght " + len);
            if (len > 0) {
                SharedPreferences pageNumPref = c.getSharedPreferences(
                        PREFS_NAME, 0);
                pageNumPref.edit().putInt("dbsize", len).commit();
            } else {
                cur.close();
                DatabaseHelper.closedatabase();
            }
            data = new String[len];
            cur.moveToNext();
            for (int i = 0; i < len; i++) {
                data[i] = cur.getString(cur.getColumnIndex("Quotes"));
                cur.moveToNext();
            }
            cur.close();
            DatabaseHelper.closedatabase();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String[]> getAllQuotes(Context c) {
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        Cursor cur = null;
        ArrayList<String[]> arList = new ArrayList<String[]>();
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();
            {
                cur = database.rawQuery("select * from LoveQuotes", null);
            }
            int len = cur.getCount();
            System.out.println("lenght " + len);
            if (len > 0) {
                SharedPreferences pageNumPref = c.getSharedPreferences(
                        PREFS_NAME, 0);
                pageNumPref.edit().putInt("dbsize", len).commit();
            } else {
                cur.close();
                DatabaseHelper.closedatabase();
            }
            cur.moveToNext();
            for (int i = 0; i < len; i++) {

                String data[] = new String[3];
                data[0] = cur.getString(0);
                data[1] = cur.getString(1);
                data[2] = cur.getInt(2) + "";
                arList.add(data);
                cur.moveToNext();
            }
            cur.close();
            DatabaseHelper.closedatabase();
            return arList;
        } catch (Exception e) {
            e.printStackTrace();
            return arList;
        }
    }

    public static String[] getQuotes(Context c, String id) {
        String data[] = new String[3];
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        Cursor cur = null;
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();
            {
                cur = database.rawQuery("select * from LoveQuotes where ID='"
                        + id + "'", null);
            }
            int len = cur.getCount();
            System.out.println("lenght " + len);
            if (len > 0) {

            } else {
                cur.close();
                DatabaseHelper.closedatabase();
                return null;
            }
            cur.moveToNext();
            for (int i = 0; i < len; i++) {

                data[0] = cur.getString(0);
                data[1] = cur.getString(1);
                data[2] = cur.getInt(2) + "";
                // cur.moveToNext();
            }
            cur.close();
            database.close();
            DatabaseHelper.closedatabase();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    public static String getQuote(Context c, String id) {
        String data = "";
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        Cursor cur = null;
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();
            {
                cur = database.rawQuery(
                        "select Quotes from LoveQuotes where ID='" + id + "'",
                        null);
            }
            int len = cur.getCount();
            System.out.println("lenght " + len);
            if (len > 0) {

            } else {
                cur.close();
                DatabaseHelper.closedatabase();
                return null;
            }
            cur.moveToNext();
            for (int i = 0; i < len; i++) {
                data = cur.getString(cur.getColumnIndex("Quotes"));
                // cur.moveToNext();
            }
            cur.close();
            database.close();
            DatabaseHelper.closedatabase();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    public static ArrayList<String[]> getAllFavQuotes(Context c) {
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        Cursor cur = null;
        ArrayList<String[]> arList = new ArrayList<String[]>();
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();
            {
                cur = database.rawQuery(
                        "select * from LoveQuotes where isFav=1", null);
            }
            int len = cur.getCount();
            System.out.println("lenght " + len);
            if (len > 0) {

            } else {
                cur.close();
                DatabaseHelper.closedatabase();
                return arList;
            }
            cur.moveToNext();
            for (int i = 0; i < len; i++) {

                String data[] = new String[3];
                data[0] = cur.getString(0);
                data[1] = cur.getString(1);
                data[2] = cur.getInt(2) + "";
                arList.add(data);
                System.out.println(data[0]);
                cur.moveToNext();
            }
            cur.close();
            database.close();
            DatabaseHelper.closedatabase();
            return arList;
        } catch (Exception e) {
            e.printStackTrace();
            return arList;
        }
    }

    public static boolean deleteFavourite(Context c, String id) {
        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);
        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();

            String sql = "update LoveQuotes set isFav=0 where ID='" + id + "' ";
            database.execSQL(sql);
            database.close();
            DatabaseHelper.closedatabase();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean insertEntry(Context c, String quote, Integer fav) {

        DatabaseHelper dbhelperShopCart = new DatabaseHelper(c);

        Cursor cur = null;

        try {
            dbhelperShopCart.createDataBase();
            SQLiteDatabase database = DatabaseHelper.openDataBase();

            cur = database.rawQuery("select Quotes from LoveQuotes", null);


            int len = cur.getCount();
            System.out.println("lenght " + len);

            ContentValues newValues = new ContentValues();
            // Assign values for each row.
            newValues.put("ID", len + 1);
            newValues.put("Quotes", quote);
            newValues.put("isFav", fav);

            // Insert the row into your table
            database.insert("LoveQuotes", null, newValues);
            ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();


            //	database.execSQL(sql);
            database.close();
            DatabaseHelper.closedatabase();
            return true;
        } catch (Exception e) {
            return false;
        }


    }


}
