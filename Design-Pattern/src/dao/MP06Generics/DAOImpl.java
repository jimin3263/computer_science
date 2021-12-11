package dao.MP06Generics;

import state.quarter.state.State;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//일반화 대상만 넣도록 한다.
public abstract class DAOImpl<D extends DBData<K>, K> implements DAO<D,K> {
    String dbTableName;
    public abstract Statement getStatement();
    public abstract String getInsertValueStr(D data);
    public abstract String getUpdateValueStr(D data);
    public abstract String getKeyColumnName();
    public abstract D getNewData(ResultSet rs);

    public DAOImpl(String dbTableName) { //기본 세팅, 연결 및 테이블 생성
        this.dbTableName = dbTableName;
    }

    @Override
    public void insert(D data) {
        try {
            String fmt = "INSERT INTO %s VALUES(%s)";
            //원래는 getter를 통해서 하나씩 필드를 가져왔지만 전체를 넘기고자 한다.
            String q = String.format(fmt, dbTableName, getInsertValueStr(data)); //valueStr -> 자식 클래스가 구현하도록 즉 abstract

            getStatement().execute(q);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<D> findAll() {
        ArrayList<D> dataList = new ArrayList<D>();
        ResultSet rs;
        try{
            rs = getStatement().executeQuery("SELECT * FROM " + dbTableName);
            while(rs.next()){
                dataList.add(getNewData(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public D findByKey(K key) {
        ResultSet rs;
        D data = null;
        try {
            String fmt = "SELECT * FROM %s WHERE %s = '%s'";
            String q = String.format(fmt, dbTableName, getKeyColumnName() ,key.toString()); //문자열로 일반화

            rs = getStatement().executeQuery(q);

            if(rs.next()){
                data = getNewData(rs);
            }
        }
        catch (SQLException e){ e.printStackTrace(); }

        return data;
    }

    @Override
    public void update(K key, D data) {
        D data2 =  findByKey(key);
        if (data2 != null) {
            try {
                String fmt = "UPDATE %s SET %s WHERE %s = '%s'";
                String q = String.format(fmt, dbTableName,getUpdateValueStr(data), getKeyColumnName() ,key.toString());
                getStatement().execute(q);
            }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void deleteByKey(K key) {
        try {
            String fmt = "DELETE FROM %s WHERE %s = '%s'";
            String q = String.format(fmt, dbTableName, getKeyColumnName() ,key.toString());
            getStatement().execute(q);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(D data) { //D에 getKey를 꼭 가지도록 해야 한다.
        deleteByKey(data.getKey());
    }
}
