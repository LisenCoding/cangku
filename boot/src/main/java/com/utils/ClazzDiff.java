package com.utils;

import com.annotation.ColumnInfo;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 对象比较
 */
public class ClazzDiff {


    /**
     * 对象比较,返回出list,obj1的属性值为空的时候自动忽略比对obj2的属性值
     * @param obj1 对象1
     * @param obj2 对象2
     * @param ignoreProperties 忽略的属性
     * @return
     */
    public  List<String> ClazzDiffColumn(Object obj1, Object obj2, @Nullable String... ignoreProperties) throws NoSuchFieldException, IllegalAccessException {

        Assert.notNull(obj1, "obj1不是空的");
        Assert.notNull(obj2, "obj2不是空的");


        List<String> list = new ArrayList<>();

        Class<?> obj1Class = obj1.getClass();
        Class<?> obj2Class = obj2.getClass();
        if (!obj1Class.isInstance(obj2)) {
            throw new IllegalArgumentException("传入的两个类不是同一个类");
        }
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);//要忽略的属性

        /**
         * 当前表
         */
        Field[] obj1DeclaredFields = obj1Class.getDeclaredFields();
        for (Field f : obj1DeclaredFields) {//ojb1的所有字段循环
            f.setAccessible(true);
            Field obj1Field = obj1Class.getDeclaredField(f.getName());
            Field obj2Field = obj2Class.getDeclaredField(f.getName());
            obj1Field.setAccessible(true);
            obj2Field.setAccessible(true);

//            属性1不为空,属性2不为空 如果传入的有ignoreProperties的话,不为空并且不包含
            if (obj1Field.get(obj1) != null &&(ignoreList == null || !ignoreList.contains(obj1Field.getName())) ) {
                ColumnInfo columnInfo = obj1Field.getAnnotation(ColumnInfo.class);
                Object o1 = obj1Field.get(obj1);
                Object o2 = obj2Field.get(obj2);

                if(!String.valueOf(o1).equals(String.valueOf(o2))){
                    if(f.getName().contains("File")){
                        list.add(columnInfo.comment()+"-->现在 : <a type='success' style='text-decoration:none' class='el-button' href='"+o1+"' >文件下载</a>,原先 : <a type='success' style='text-decoration:none' class='el-button' href='"+o2+"' >文件下载</a>");
                    }else if(f.getName().contains("Video")){
                        list.add(columnInfo.comment()+"-->现在 : <video src='"+o1+"' width='100px' height='100px' controls='controls'></video>,原先 : <video src='"+o2+"' width='100px' height='100px' controls='controls'></video>");
                    }else if(f.getName().contains("Photo")){
                        list.add(columnInfo.comment()+"-->现在 : <img src='"+o1+"' width='100px' height='100px'>,原先 : <img src='"+o2+"' width='100px' height='100px'>");
                    }else if(f.getName().contains("Time")){
                        list.add(columnInfo.comment()+"-->现在 : ["+ DateUtils.format((Date) o1,"yyyy-MM-dd")+"],原先 : ["+DateUtils.format((Date) o2,"yyyy-MM-dd")+"]");
                    }else{
                        list.add(columnInfo.comment()+"-->现在 : ["+o1+"],原先 : ["+o2+"]");
                    }
                }
            }
        }



        /**
         * 父表
         */
        Field[] obj1DeclaredFields2 = obj1Class.getSuperclass().getDeclaredFields();
        for (Field f : obj1DeclaredFields2) {//ojb1的所有字段循环
            f.setAccessible(true);
            Field obj1Field = obj1Class.getSuperclass().getDeclaredField(f.getName());
            Field obj2Field = obj2Class.getSuperclass().getDeclaredField(f.getName());
            obj1Field.setAccessible(true);
            obj2Field.setAccessible(true);

//            属性1不为空,属性2不为空 如果传入的有ignoreProperties的话,不为空并且不包含
            if (obj1Field.get(obj1) != null &&(ignoreList == null || !ignoreList.contains(obj1Field.getName())) ) {
                ColumnInfo columnInfo = obj1Field.getAnnotation(ColumnInfo.class);
                Object o1 = obj1Field.get(obj1);
                Object o2 = obj2Field.get(obj2);

                if(!String.valueOf(o1).equals(String.valueOf(o2))){
                    if(f.getName().contains("File")){
                        list.add(columnInfo.comment()+"-->现在 : <a type='success' style='text-decoration:none' class='el-button' href='"+o1+"' >文件下载</a>,原先 : <a type='success' style='text-decoration:none' class='el-button' href='"+o2+"' >文件下载</a>");
                    }else if(f.getName().contains("Video")){
                        list.add(columnInfo.comment()+"-->现在 : <video src='"+o1+"' width='100px' height='100px' controls='controls'></video>,原先 : <video src='"+o2+"' width='100px' height='100px' controls='controls'></video>");
                    }else if(f.getName().contains("Photo")){
                        list.add(columnInfo.comment()+"-->现在 : <img src='"+o1+"' width='100px' height='100px'>,原先 : <img src='"+o2+"' width='100px' height='100px'>");
                    }else if(f.getName().contains("Time")){
                        list.add(columnInfo.comment()+"-->现在 : ["+ DateUtils.format((Date) o1,"yyyy-MM-dd")+"],原先 : ["+DateUtils.format((Date) o2,"yyyy-MM-dd")+"]");
                    }else{
                        list.add(columnInfo.comment()+"-->现在 : ["+o1+"],原先 : ["+o2+"]");
                    }
                }
            }
        }
        return  list;
    }



    /**
     * 判断本实体有没有这个字段
     * @param c
     * @param fieldName
     * @return
     */
    public boolean hasField(Class c, String fieldName){
        Field[] fields = c.getDeclaredFields();

        for (Field f : fields) {
            if (fieldName.equals(f.getName())) {
                return true;

            }

        }

        return false;
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

//        ChengpinEntity chengpinEntity1 = new ChengpinEntity();
//
//        chengpinEntity1.setId(2);
//        chengpinEntity1.setXiaoshourenyuanId(3);
//        chengpinEntity1.setChengpinUuidNumber("4");
//        chengpinEntity1.setChengpinName("6");
//        chengpinEntity1.setChengpinSchool("7");
//        chengpinEntity1.setChengpinLianxifangshi("8");
//        chengpinEntity1.setChengpinXiaoshouJine(9.0);
//        chengpinEntity1.setChengpinTichengJine(10.0);
//        chengpinEntity1.setLunwenTypes(11);
//        chengpinEntity1.setLunwenXiaoshouJine(12.1);
//        chengpinEntity1.setLunwenTichengJine(13.1);
//        chengpinEntity1.setChengpinZhuangtaiTypes(14);
//        chengpinEntity1.setChengpinFile("15");
//        chengpinEntity1.setChengpinText("16");
//        chengpinEntity1.setChengpinDelete(1);
//        chengpinEntity1.setInsertTime(new Date());
//        chengpinEntity1.setUpdateTime(null);
//        chengpinEntity1.setCreateTime(null);
//
//
//
//
//        ChengpinEntity chengpinEntity2 = new ChengpinEntity();
//
//        chengpinEntity2.setId(3);
//        chengpinEntity2.setXiaoshourenyuanId(4);
//        chengpinEntity2.setChengpinUuidNumber("4");
//        chengpinEntity2.setChengpinName("6");
//        chengpinEntity2.setChengpinSchool("7");
//        chengpinEntity2.setChengpinLianxifangshi("8");
//        chengpinEntity2.setChengpinXiaoshouJine(9.0);
//        chengpinEntity2.setChengpinTichengJine(10.0);
//        chengpinEntity2.setLunwenTypes(11);
//        chengpinEntity2.setLunwenXiaoshouJine(12.1);
//        chengpinEntity2.setLunwenTichengJine(13.1);
//        chengpinEntity2.setChengpinZhuangtaiTypes(14);
//        chengpinEntity2.setChengpinFile("16");
//        chengpinEntity2.setChengpinText("16");
//        chengpinEntity2.setChengpinDelete(1);
//        chengpinEntity2.setInsertTime(null);
//        chengpinEntity2.setUpdateTime(new Date());
//        chengpinEntity2.setCreateTime(null);


//        List<String> strings = new ClazzDiff<ChengpinEntity>().ClazzDiffColumn(chengpinEntity1, chengpinEntity2,new String[]{"serialVersionUID"});
//        List<String> strings = new ClazzDiff().ClazzDiffColumn(chengpinEntity1, chengpinEntity2);
//        System.out.println(strings);


    }

}
