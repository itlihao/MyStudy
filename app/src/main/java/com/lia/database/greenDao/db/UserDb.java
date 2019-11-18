package com.lia.database.greenDao.db;

import android.content.Context;

import com.lia.mystudy.model.User;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class UserDb {

    private Context mContext;

    private static volatile UserDb instance;

    private UserDb(Context context) {
        this.mContext = context;
    }

    public static UserDb getInstance(Context context) {
        if (instance == null) {
            synchronized (UserDb.class) {
                if (instance == null) {
                    instance = new UserDb(context);
                }
            }
        }
        return instance;
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(User user) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(mContext).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(mContext).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(User user) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(mContext).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.delete(user);
    }


    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(User user) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(mContext).getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.update(user);
    }

    /**
     * loadAll()：查询所有数据。
     * queryRaw()：根据条件查询。
     * queryBuilder() : 方便查询的创建。
     * QueryBuilder的常见方法
     * where(WhereCondition cond, WhereCondition... condMore): 查询条件，参数为查询的条件！
     * or(WhereCondition cond1, WhereCondition cond2, WhereCondition... condMore): 嵌套条件或者，用法同or。
     * and(WhereCondition cond1, WhereCondition cond2, WhereCondition... condMore): 嵌套条件且，用法同and。
     * join(Property sourceProperty, Class<J> destinationEntityClass):多表查询，后面会讲。
     * 输出结果有四种方式，选择其中一种最适合的即可，list()返回值是List,而其他三种返回值均实现Closeable,需要注意的不使用数据时游标的关闭操作：
     * list （）所有实体都加载到内存中。结果通常是一个没有魔法的 ArrayList。最容易使用。
     * listLazy （）实体按需加载到内存中。首次访问列表中的元素后，将加载并缓存该元素以供将来使用。必须关闭。
     * listLazyUncached （）实体的“虚拟”列表：对列表元素的任何访问都会导致从数据库加载其数据。必须关闭。
     * listIterator （）让我们通过按需加载数据（懒惰）来迭代结果。数据未缓存。必须关闭。
     * orderAsc() 按某个属性升序排；
     * orderDesc() 按某个属性降序排；
     */

    /**
     * GreenDao中SQL语句的缩写
     * eq()："equal ('=?')" 等于；
     * notEq() ："not equal ('<>?')" 不等于；
     * like()：" LIKE ?" 值等于；
     * between()：" BETWEEN ? AND ?" 取中间范围；
     * in()：" IN (" in命令;
     * notIn()：" NOT IN (" not in 命令;
     * gt()：">?" 大于;
     * lt()："<? " 小于;
     * ge()：">=?" 大于等于;
     * le()："<=? " 小于等于;
     * isNull()：" IS NULL" 为空;
     * isNotNull()：" IS NOT NULL" 不为空;
     */

    public List<User> queryAll() {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(mContext).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.loadAll(User.class);
    }

    /**
     * 查询用户列表
     */
    public List<User> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(mContext).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        return qb.list();
    }

    /**
     * 查询用户列表
     */
    public List<User> queryUserList(int age) {
        DaoMaster daoMaster = new DaoMaster(DBManager.getInstance(mContext).getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);
        return qb.list();
    }
}
