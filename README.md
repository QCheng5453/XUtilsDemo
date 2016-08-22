# XUtils3 Demo 说明文档

标签（空格分隔）： Android Android_Frameworks XUtils3

---


**Key Words:** `XUtils3` `IOC` `Annotation`

**References:**

- 官方介绍 
[https://github.com/wyouflf/xUtils3](https://github.com/wyouflf/xUtils3)
- XUtil源码简析 
[http://a.codekk.com/detail/Android/Caij/xUtils%20%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90](http://a.codekk.com/detail/Android/Caij/xUtils%20%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90)
- XUtils3基本用法 
[http://blog.csdn.net/a1002450926/article/details/50341173](http://blog.csdn.net/a1002450926/article/details/50341173)
[http://www.androidchina.net/4177.html?utm_source=tuicool&utm_medium=referral](http://www.androidchina.net/4177.html?utm_source=tuicool&utm_medium=referral)
- 公共知识点
    - IOC简介 
[http://www.cnblogs.com/DebugLZQ/archive/2013/06/05/3107957.html](http://www.cnblogs.com/DebugLZQ/archive/2013/06/05/3107957.html)
    - Java Annotation
[http://a.codekk.com/detail/Android/Trinea/%E5%85%AC%E5%85%B1%E6%8A%80%E6%9C%AF%E7%82%B9%E4%B9%8B%20Java%20%E6%B3%A8%E8%A7%A3%20Annotation](http://a.codekk.com/detail/Android/Trinea/%E5%85%AC%E5%85%B1%E6%8A%80%E6%9C%AF%E7%82%B9%E4%B9%8B%20Java%20%E6%B3%A8%E8%A7%A3%20Annotation)
    - Java 动态代理
[http://a.codekk.com/detail/Android/Caij/%E5%85%AC%E5%85%B1%E6%8A%80%E6%9C%AF%E7%82%B9%E4%B9%8B%20Java%20%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86](http://a.codekk.com/detail/Android/Caij/%E5%85%AC%E5%85%B1%E6%8A%80%E6%9C%AF%E7%82%B9%E4%B9%8B%20Java%20%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86)
    - DDMS 基本使用
[https://developer.android.com/studio/profile/ddms.html](https://developer.android.com/studio/profile/ddms.html)
    - Charles 文档
[https://www.charlesproxy.com/documentation/](https://www.charlesproxy.com/documentation/)

---

## 预先准备

- gradle添加依赖
`compile 'org.xutils:xutils:3.3.36'`

- mainfests添加权限
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

- 继承`Application`类并覆写`onCreate`函数
```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this); // XUtils初始化
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
    }
}
```
在AndroidMainfest.xml中添加`android:name=”.MyApplication”`

## Annotation / View

### 功能简介

- 使用注解(Annotation)简化开发流程

### 基本用法

注: BaseActivity继承AppCompatActivity并在onCreate函数中添加`x.view().inject(this);`用于View的注入。

#### 1. @ContentView
```java
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity{}
```
类同于
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
}
```

#### 2. @ViewInject
将View与相应id的控件绑定
```java
@ViewInject(R.id.container)
private ViewPager mViewPager;

@ViewInject(R.id.toolbar)
private Toolbar toolbar;
```
类同于
```java
private ViewPager mViewPager;
private Toolbar toolbar;
@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    mViewPager = (ViewPager)findViewById(R.id.container);
    toolbar = (Toolbar)findViewById(R.id.toolbar);
}
```
#### 3. @Event 绑定事件与监听器
```java
/**
 * 1. 方法必须私有限定,
 * 2. 方法参数形式必须和type对应的Listener接口一致.
 * 3. 注解参数value支持数组: value={id1, id2, id3}
 * 4. 其它参数说明见{@link org.xutils.view.annotation.Event}类的说明.
 **/
@Event(value = R.id.btn_test_baidu,
        type = View.OnClickListener.class/*可选参数, 默认是View.OnClickListener.class*/)
private void onTestBaiduClick(View view) {
...
}
```
类同于
```java
View view = findViewById(R.id.btn_test_baidu);
view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    ...
    }
});
```

#### 4. Fragment的注解
```java
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //  将inflater填充器作为参数传入inject()，返回View
    return x.view().inject(this,inflater,container);
}
```

### 补充说明

#### 效率问题
- XUtils3 Annotation内部使用Java反射动态创建类对象来实现功能。
减少代码量的同时，牺牲了一定的运行效率。

- 效率测试：
绑定1000个Listener，使用XUtils3 Annotation需要约600ms，不使用Annotatio只需约40ms。
（Nexus 5, Android 6.0.1）

#### 可选替代方案
- 优点：减少代码量
- 缺点：一定程度上降低了效率
- 可供替代方案：
    - Lambda expression : 
 Java 8 Lambda表达式教程[http://blog.csdn.net/ioriogami/article/details/12782141/](http://blog.csdn.net/ioriogami/article/details/12782141/)
在Android Studio中使用Lambda表达式[http://www.bubuko.com/infodetail-983988.html](http://www.bubuko.com/infodetail-983988.html)

    - 其他开发框架和库：AndroidAnnotations, Butterknife等
    
## Http

### 功能简介



#### 1. Get 请求
```java
//设置参数
RequestParams params = new RequestParams("http://www.transsion.com/");
//异步访问网络
x.http().get(params, new Callback.CommonCallback<String>() {
    @Override
    public void onSuccess(String result) {
        //连接成功时回调，主线程
        textview_to_view_html.setText(result);
    }
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //连接错误时回调
    }
    @Override
    public void onCancelled(Callback.CancelledException cex) {
        //取消时回调，可以手动取消连接
    }
    @Override
    public void onFinished() {
        //结束时回调，无论成功或者错误
    }
});
```

#### 2. Post 
```java
String stu_cir_url = "http://xk.urp.seu.edu.cn/jw_service/service/stuCurriculum.action";
RequestParams params = new RequestParams(stu_cir_url);
// 增加参数
params.addBodyParameter("returnStr","");
params.addParameter("queryStudentId","213132251");
params.addParameter("queryAcademicYear","15-16-2");
x.http().post(params, new Callback.CommonCallback<String>() {
    // ...
});
```
#### 3. 其他请求方式
```java
// 第一个参数可以定义请求的方式
x.http().request(HttpMethod.PUT, params, new Callback.CommonCallback<String>() {
    // ...
});
```
#### 4. 文件上传
```java
String path="/mnt/sdcard/Download/star.jpg";
RequestParams params = new RequestParams(url);
//  设置为Multipart形式进行文件上传
params.setMultipart(true);
params.addBodyParameter("file",new File(path));
x.http().post(params, new Callback.CommonCallback<String>() {
    //  ...
});
```
#### 5. 文件下载
```java
// 需要下载文件的地址
url = "http://img4.imgtn.bdimg.com/it/u=3419365682,3179513322&fm=21&gp=0.jpg";
RequestParams params = new RequestParams(url);
// 下载写入的地址
final String fileName = Environment.getExternalStorageDirectory()+"/Download/star_2.png";
params.setSaveFilePath(fileName);
params.setAutoRename(false);
// 使用ProgressCallback可以监控文件下载的进度
x.http().get(params, new Callback.ProgressCallback<File>() {
    @Override
    public void onSuccess(File result) {
        //System.out.println("Download Success");
        // 当图片下载完成后，显示出来
        ImageOptions options = new ImageOptions.Builder()
                .setCrop(true)
                .setSize(800, 500)
                .build();
        x.image().bind(imageView,fileName,options);
    }
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        //System.out.println("Download Error"+"    "+ex.toString());
    }
    @Override
    public void onCancelled(CancelledException cex) {
    }
    @Override
    public void onFinished() {
    }
    @Override
    public void onWaiting() {
    }
    @Override
    public void onStarted() {
    }
    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        // 用于监视下载的进度
    }
});
```

#### 6. 使用缓存
```java
String url = "http://www.transsion.com";
RequestParams params = new RequestParams(url);
// 设置缓存存在的最大周期
params.setCacheMaxAge(1000*60);
Callback.Cancelable cancelable = x.http().get(params, new Callback.CacheCallback<String>() {
    // ...
    
    // 缓存时调用
    @Override
    public boolean onCache(String result) {
        //false 不相信本地缓存
        //true 相信本地缓存
        Log.i("houn.xu","cache<<"+result);
        return true;
    }
});
```
### 补充说明

Http请求回首先查看缓存，如果缓存存在并选择相信缓存则会直接将数据传递至主线程，否则则会开启网络请求、传输处理数据再将数据返回至主线程。


## Image

### 基本用法

#### 1. 图片绑定

```java
// 设置图片加载的参数
// 更多设置选项参考源码ImageOptions.Builder对象
ImageOptions options = new ImageOptions.Builder()
        .setAnimation(null) // 设置动画
        .setAutoRotate(false)   // 设置是否自动旋转
        .setCircular(false) // 设置是否显示为圆形
        .setCrop(true)  // 设置大小是否起作用
        .setSize(500,400) // 设置图片显示大小
        .setIgnoreGif(false)    // 设置是否忽略gif动画
        .setUseMemCache(true)   // 设置是否使用内存缓存
        .build();   //构建options对象

// 将ImageView对象与资源地址进行绑定显示
// bind有四个重载函数，这是最复杂的一个
// 第一个参数声明用于显示的ImageView，第二个用于声明将要绑定的资源url，第三个参数设置图片加载的选项，第四个传入CallBack对象，用于绑定时的回调
x.image().bind(image, url, options, new Callback.CommonCallback<Drawable>() {
    @Override
    public void onSuccess(Drawable result) {
    }
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
    }
    @Override
    public void onCancelled(CancelledException cex) {
    }
    @Override
    public void onFinished() {
    }
});
```

#### 2. LoadFile
```java
x.image().loadFile(urls[0], options, new Callback.CacheCallback<File>() {
    @Override
    public boolean onCache(File result) {
        // 可以进行图片另存为 等操作
        // 是否相信缓存
        return true;
    }
    @Override
    public void onSuccess(File result) {
    }
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
    }
    @Override
    public void onCancelled(CancelledException cex) {
    }
    @Override
    public void onFinished() {
    }
});
```

## Database

### 基本用法

#### 1. 数据库的创建和基本设置
```java
DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
        .setDbName("jike.db")   // 设置数据库名字
        // 设置表创建的监听器
        .setTableCreateListener(new DbManager.TableCreateListener() {
            @Override
            public void onTableCreated(DbManager db, TableEntity<?> table) {
                // 当表创建时回调
            }
        })
//      .setDbDir(new File("/mnt/sdcard/"))   // 设置数据库创建的路径，有默认值
//      .setDbVersion(1)  // 设置数据库的版本
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
```

#### 2. 基本操作（增删查改）
```java
ArrayList<ChildInfo> childInfos = new ArrayList<>();
childInfos.add(new ChildInfo("name1", 21));
childInfos.add(new ChildInfo("name2", 22));
childInfos.add(new ChildInfo("name3", 23));
//  XUtils 采用ORM框架，可以直接讲对象添加至数据库
db.save(childInfos);
//  删除表
//  db.dropTable(ChildInfo.class);
//  删除数据库
//  db.dropDb();

//  数据库查询操作
//  WhereBuiler用于构建数据库查询的筛选条件
WhereBuilder b = WhereBuilder.b();
//  用于查询age小于25大于22的数据
b.and("age",">",22);
b.and("age","<",25);
//  筛选限定的数据
List<ChildInfo> all = db.selector(ChildInfo.class).where(b).findAll();
for(ChildInfo childInfo :all){
    // operations
}

//  修改表中数据内容
ChildInfo first = db.findFirst(ChildInfo.class);
first.setAge(10000);
db.saveOrUpdate(first);

//  删除表中数据
db.delete(ChildInfo.class);
```

#### 3. 构建一对多、多对多的关系
```java
//  构建一对多的数据关系
String deptId = "abcd";
Department department = new Department(deptId,"设计部");
Employee employee = new Employee(UUID.randomUUID() + "", "张三", deptId);
Employee employee2 = new Employee(UUID.randomUUID() + "", "李四", deptId);
db.save(department);
db.save(employee);
db.save(employee2);

//  返回DbModel，用于查询复杂数据库关系
//  查询用的sql语句
String sql = "select id,emp_id,emp_name from employee where dept_id = 'abcd'";
//  返回DbModel形式的查询
List<DbModel> dbModelAll = db.findDbModelAll(new SqlInfo(sql));
for (DbModel dbmodel : dbModelAll) {
    String id = dbmodel.getString("id");
    String emp_id = dbmodel.getString("emp_id");
    String emp_name = dbmodel.getString("emp_name");
}

//  多对多的数据关系采用中间表实现，具体见demo
```

## 写在最后

使用快速开发框架能够减少代码量，提高开发速度，但一定程度上牺牲了性能和代码的可拓展性。



\*Demo为极客学院实例工程修改




    


 
 

 

