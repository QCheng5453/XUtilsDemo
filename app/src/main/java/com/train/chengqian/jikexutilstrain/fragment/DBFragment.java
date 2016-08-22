package com.train.chengqian.jikexutilstrain.fragment;


import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.train.chengqian.jikexutilstrain.R;
import com.train.chengqian.jikexutilstrain.domain.ChildInfo;
import com.train.chengqian.jikexutilstrain.domain.Department;
import com.train.chengqian.jikexutilstrain.domain.Employee;
import com.train.chengqian.jikexutilstrain.domain.RTeacherStudent;
import com.train.chengqian.jikexutilstrain.domain.Student;
import com.train.chengqian.jikexutilstrain.domain.Teacher;

import org.xutils.DbManager;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.db_view)
public class DBFragment extends Fragment {
    // 配置DbManager的参数
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("jike.db")   // 设置数据库名字
            // 设置表创建的监听器
            .setTableCreateListener(new DbManager.TableCreateListener() {
                @Override
                public void onTableCreated(DbManager db, TableEntity<?> table) {
                    // 当表创建时回调
                }
            })
//            .setDbDir(new File("/mnt/sdcard/"))   // 设置数据库创建的路径，有默认值
//            .setDbVersion(1)  // 设置数据库的版本
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    // 数据库更新时回调
                }
            })
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    // 数据库打开时回调
                }
            });
    // 根据配置创建DbManager
    DbManager db = x.getDb(daoConfig);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 6、修改表中的一条数据
     * 7、查询表中的数据
     * 8、删除表中的数据
     */
    //1、创建数据库  3、创建表 5、往数据库中的表插入一条数据
    @Event(R.id.create_db)
    private void createDB(View view) throws DbException {
        ArrayList<ChildInfo> childInfos = new ArrayList<>();
        childInfos.add(new ChildInfo("jike1", 21));
        childInfos.add(new ChildInfo("jike2", 22));
        childInfos.add(new ChildInfo("jike3", 23));
        childInfos.add(new ChildInfo("jike4", 24));
        childInfos.add(new ChildInfo("jike5", 25));
        childInfos.add(new ChildInfo("jike6", 26));
        childInfos.add(new ChildInfo("jike7", 27));
        childInfos.add(new ChildInfo("jike8", 28));
        db.save(childInfos);
    }
    //    2、删除数据库
    @Event(R.id.del_db)
    private void delDB(View view) throws DbException {
        db.dropDb();
    }

    //    4、删除表
    @Event(R.id.del_table)
    private void delTable(View view) throws DbException {
        db.dropTable(ChildInfo.class);
    }

    //7、查询表中的数据
    @Event(R.id.select_table)
    private void selelctDB(View view) throws DbException {
        ChildInfo first = db.findFirst(ChildInfo.class);
        //age >22 <<25
        //  数据库查询操作
        //  WhereBuiler用于构建数据库查询的筛选条件
//        WhereBuilder b = WhereBuilder.b();
//        //  用于查询age小于25大于22的数据
//        b.and("age", ">", 22);
//        b.and("age", "<", 25);
//        //  筛选限定的数据
//        List<ChildInfo> all = db.selector(ChildInfo.class).where(b).findAll();
//        for (ChildInfo childInfo : all) {
//            // operations
//        }
    }

//6、修改表中的一条数据
@Event(R.id.update_table)
private void updateTable(View v) throws DbException {
    ChildInfo first = db.findFirst(ChildInfo.class);
//        first.setcName("张三100");
//        first.setAge(100);
//        db.update(first,"age","c_name");
//        WhereBuilder b = WhereBuilder.b();
//        b.and("id","=",first.getId());
//        KeyValue age = new KeyValue("age", 1000);
//        KeyValue name = new KeyValue("c_name","张三1000");
//        db.update(ChildInfo.class,b,age,name);

    first.setcName("张三10000");
    first.setAge(10000);
    db.saveOrUpdate(first);

}

    //    8、删除表中的数据
    @Event(R.id.del_table_data)
    private void delTableData(View view) throws DbException {
        db.delete(ChildInfo.class);
//        db.delete(ChildInfo.class,);
    }


    //保存一对多的关系
    @Event(R.id.save_one_to_many)
    private void saveOneToMany(View view) throws DbException {
        String deptId = "abcd";
        Department department = new Department(deptId, "设计部");

        Employee employee = new Employee(UUID.randomUUID() + "", "张三", deptId);
        Employee employee2 = new Employee(UUID.randomUUID() + "", "李四", deptId);
        Employee employee3 = new Employee(UUID.randomUUID() + "", "王五", deptId);

        db.save(department);
        db.save(employee);
        db.save(employee2);
        db.save(employee3);

    }
    // 保存多对多的关系
    @Event(R.id.save_many_to_many)
    private void saveManyToMany(View view) throws DbException {
        String java1 = UUID.randomUUID() + "";
        String java2 = UUID.randomUUID() + "";
        String android1 = UUID.randomUUID() + "";
        String android2 = UUID.randomUUID() + "";
        Student sJava1 = new Student(20, java1, "极客Java学生一");
        Student sJava2 = new Student(21, java2, "极客Java学生二");
        Student sAndroid1 = new Student(22, android1, "极客Android学生一");
        Student sAndroid2 = new Student(23, android2, "极客Android学生二");

        String aId = UUID.randomUUID() + "";
        String jId = UUID.randomUUID() + "";
        Teacher ateacher = new Teacher("极客Android老师", aId);
        Teacher jteacher = new Teacher("极客Java老师", jId);

        db.save(sJava1);
        db.save(sJava2);
        db.save(sAndroid1);
        db.save(sAndroid2);

        db.save(ateacher);
        db.save(jteacher);

        // 用RTeacherStudent作为中间表
        db.save(new RTeacherStudent(java1, jId));
        db.save(new RTeacherStudent(java2, jId));
        db.save(new RTeacherStudent(android1, aId));
        db.save(new RTeacherStudent(android2, aId));
        db.save(new RTeacherStudent(android1, jId));
        db.save(new RTeacherStudent(android2, jId));
    }

    //返回dbModle形式的查询
    @Event(R.id.select_many_to_many)
    private void selectOneToMany(View v) throws DbException {
        //查询设计部所有的成员信息
        String sql = "select id,emp_id,emp_name from employee where dept_id = 'abcd'";
        List<DbModel> dbModelAll = db.findDbModelAll(new SqlInfo(sql));
        for (DbModel dbmodel : dbModelAll) {
            String id = dbmodel.getString("id");
            String emp_id = dbmodel.getString("emp_id");
            String emp_name = dbmodel.getString("emp_name");
            Log.i("houn.xu", "id<<" + id + " empName<<" + emp_name + " emp_id<<" + emp_id);
        }
    }
}