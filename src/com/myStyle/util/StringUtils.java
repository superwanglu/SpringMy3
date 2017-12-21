package com.myStyle.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtils
{

    public static List<String> tokenizeToList(String url, String token)
    {
        if (StringUtils.isEmpty(url))
        {
            return null;
        }

        StringTokenizer st = new StringTokenizer(url, token);
        List<String> urlList = new ArrayList<String>();
        while (st.hasMoreTokens())
        {
            String item = st.nextToken().trim();
            if (item.length() > 0)
            {
                urlList.add(item);
            }
        }

        return urlList;
    }

    public static String clearUrl(String url)
    {
        if (StringUtils.isEmpty(url))
        {
            return null;
        }
        int index = url.lastIndexOf("/");

        if (index == -1)
        {
            return url;
        }

        return url.substring(index + 1, url.length());
    }

    public static boolean isEmpty(String str)
    {
        if (str == null || str.trim().length() <= 0 || str.equals("null"))
        {
            return true;
        }

        return false;
    }

    public static int toInt(Object obj)
    {
        try
        {
            return Integer.parseInt(obj.toString());
        }
        catch (Exception ex)
        {
            return 0;
        }
    }

    /**
     * 将字符串时间转换成java.util.Date类型
     * 
     * @param str
     *            要转换的字符
     * @param format
     *            时间格式
     * @return 如果转换失败，返回null
     */
    public static Date string2Date(String str, String format)
    {
        if (isEmpty(str) || isEmpty(format))
        {
            return null;
        }

        // 定义日期/时间格式
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date;
        try
        {
            // 转换日期/时间格式
            date = sdf.parse(str);

            if (!str.equals(sdf.format(date)))
            {
                date = null;
            }
        }
        catch (ParseException e)
        {
            date = null;
        }

        return date;
    }

    public static String getMathAndTime()
    {
        String num = String.valueOf(Math.round(Math.random() * 1000000));
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String time = sf.format(date);// 当前时间
        return time + num;
    }

    public static String formatDateToString(Date date, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 判断某年的2月有多少天
     */
    public static int getFebruary(int year)
    {
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
        {
            return 29;
        }
        return 28;
    }

    /**
     * 计算时间段相隔多少天
     */
    public static int getNumberOfDays(String arrangementStartTime,
            String arrangementEndTime)
    {
        long daysBetween = 0;
        if (!arrangementStartTime.equals("") && !arrangementEndTime.equals(""))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            try
            {
                Date d1 = sdf.parse(arrangementStartTime);
                Date d2 = sdf.parse(arrangementEndTime);
                daysBetween = (d2.getTime() - d1.getTime() + 1000000)
                        / (3600 * 24 * 1000);
            }
            catch (ParseException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return (int) daysBetween;
    }

    public static int getDayByDate(String date)
    {
        int year = 0, month = 0, day = 0;
        int total = 0;
        int i;

        if (date != null && !date.equals(""))
        {
            year = Integer.parseInt(date.substring(0, 4));
            month = Integer.parseInt(date.substring(5, 7));
            day = Integer.parseInt(date.substring(8));
        }

        int a[] = { 31, getFebruary(year), 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31 };

        // System.out.println("The date you input is:" + year + " " + month +
        // " "
        // + day);

        if (month == 1)
        {
            total = day;
        }
        else
        {
            for (i = 0; i < (month - 1); i++)
            {
                total = total + a[i];
            }

            total = total + day;

        }
        return total;
    }


    // 判断特殊字符，有的话true 否则没有 false
    public static Boolean StringFilter_Bool(String str)
            throws PatternSyntaxException
    {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        boolean bool = matcher.find();
        return bool;
    }

    // 过滤特殊字符
    public static String StringFilter(String str) throws PatternSyntaxException
    {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * 
     * @param value
     *            指定的字符串
     * @return 字符串的长度
     */
    public static int getStringLength(String value)
    {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++)
        {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese))
            {
                /* 中文字符长度为2 */
                valueLength += 2;
            }
            else
            {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static boolean isChinese(String str)
    {
        Pattern pattern = Pattern.compile("[\\u3400-\\u9FBF]+");// 是否中文表达式
        if (str == null)
            return false;

        if (pattern.matcher(str.trim()).find())
            return true;
        else
            return false;
    }

    /**
     * @param args
     */
    public static int getMaxDayByMonth(String date)
    {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7)) - 1;
        int a[] = { 31, getFebruary(year), 31, 30, 31, 30, 31, 31, 30, 31, 30,
                31 };
        return a[month];

    }

    public static boolean isInteger(String str)
    {

        try
        {
            // 把字符串强制转换为数字
            int num = Integer.valueOf(str);
            if (num > 0)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            // 如果抛出异常，返回False
            return false;
        }
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getNowDateToStr(String formartStr)
    {
        if(StringUtils.isEmpty(formartStr))
        {
            formartStr= "yyyyMMddHHmmss";
        }
        SimpleDateFormat simpDate = new SimpleDateFormat(formartStr);
        String str = simpDate.format(new Date());
        return str;
    }
    /**
     * 去除字符串右边指定标记后的内容
     * @return
     */
    public static String getLastStr(String source, String mark){
        if(StringUtils.isEmpty(mark) || StringUtils.isEmpty(source))
        {
            return source;
        }
        
        int index = source.lastIndexOf(mark);
        
        return source.substring(index + 1, source.length());
    }
    /**
     * 去除字符串右边指定标记后的内容
     * @return
     */
    public static String cutRightSpecifiedStr(String source, String mark){
        if(StringUtils.isEmpty(mark) || StringUtils.isEmpty(source))
        {
            return source;
        }
        
        int index = source.lastIndexOf(mark);
        
        return source.substring(0, index + 1);
    }
    
    
    /**
     * 解析字符串
     * @param str $(resume.Height}
     * @return <table,"resume"> <cloumn,"Height">
     */
    public static Map<String,String> formatStrToMap(String str){
        Map<String,String> mapStr = new HashMap<String,String>();
        if(!isEmpty(str)){
            int t = str.indexOf("{")+1;
            int p = str.indexOf(".");
            int c = str.indexOf("}");
            String tableStr = str.substring(t, p);
            String columnStr = str.substring(p+1,c);
            mapStr.put("table", tableStr);
            mapStr.put("cloumn", columnStr);
            return mapStr;
        }
        return null;
    }
    /**
     * 取小数点前字符串
     * @param pointStr
     * @return
     */
    public static String getPointBefStr(String pointStr){
        int p = pointStr.indexOf(".");
        if(p <= 0){
            return pointStr;
        }
        String str = pointStr.substring(0,p);
        return str;
    }
    /**
     * 取小数点后字符串
     * @param pointStr
     * @return
     */
    public static String getPointEndStr(String pointStr){
        int p = pointStr.indexOf(".");
        if(p <= 0){
            return pointStr;
        }
        String str = pointStr.substring(p+1,pointStr.length());
        return str;
    }
    
    /**
     * 判断字符串是否包括${格式
     * @param str
     * @return
     */
    public static boolean strConFormat(String str){
        if(isEmpty(str))
        {
            return false;
        }
        
        int temp = str.indexOf("${");
        if (temp >= 0) 
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    


    /**
     * 随机生成6位数字
     */
    public static String getRandomNum()
    {
        StringBuffer strbufguess = new StringBuffer();
        String strguess = new String();
        int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Random rannum = new Random();
        int count;
        int i = 0, temp_i = 0;
        for (int j = 10; j > 4; j--)
        {
            i = 0;
            temp_i = 0;
            count = rannum.nextInt(j);
            while (i <= count)
            {
                if (nums[temp_i] == -1)
                    temp_i++;
                else
                {
                    i++;
                    temp_i++;
                }
            }
            strbufguess.append(Integer.toString(nums[temp_i - 1]));
            nums[temp_i - 1] = -1;
        }
        strguess = strbufguess.toString();
        rannum = null;
        strbufguess = null;
        nums = null;
        return strguess;
    }
    
    
    /**
     * @category 搜索替换特殊字符\为\\
     */
    public static String replaceSPchar(String str)
    {
    	if(str != null && !str.isEmpty())
    	{
    		if(!str.equals("\\"))
    		{
    			str = str.replace("\\", "\\\\");
    		}
    	}
    	return str;
    }
    
    /**
     * 保留两位小数点后两位
     */
    public static double obtainNumonlyTwo(double d)
    {
    	BigDecimal bg = new BigDecimal(d);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
    
    /**
     * 半角双引号与单引号的转换
     * 
     */
    public static String convertQuote(String source)
    {
        if(StringUtils.isEmpty(source))
        {
            return source;
        }
        
        source = source.replace("\"","&quot;").replace("'","&#039;");
        
        return source;
    }
    
    
    /**
     * 获取年份有多少天
     * @param date
     * @return
     */
    public static int getMaxDayByYear(String date)
	{
		int yearDays = 0;
	    int year     = Integer.parseInt(date.substring(0, 4));
	    yearDays     = 337 + StringUtils.getFebruary(year);
	    return yearDays;
	}
    
    
	/**
     * 获取转义后的查询名称
     * @param name
     * @return
     */
    public static String getEscapeName(String name){
    	String escapName = name;
    	if(escapName != null){

    		if(name.contains("[")){
    			escapName = name.replaceAll("\\[", "[[]"); 
    			name = escapName;
    		}
    		if(name.contains("_")){
    			escapName = name.replaceAll("_", "[_]"); 
    			name = escapName;
    		}
    		if(name.contains("%")){
    			escapName = name.replaceAll("%", "[%]"); 
    			name = escapName;
    		}
    		if(name.contains("'")){
    			escapName = name.replaceAll("'", "''"); 
    			name = escapName;
    		}
    	}
    	return escapName;
    }
    
    /**
     * 获取转义后的插入名称
     * @param name
     * @return
     */
    public static String getInsertEscapeName(String name){
    	String escapName = name;
    	if(escapName != null){
    		if(name.contains("'")){
    			escapName = name.replaceAll("'", "''"); 
    			name = escapName;
    		}
    	}
    	return escapName;
    }
    
    /**
     * URL传参中文转码
     */
    public static String decodeStr(String str)
    {
        String rtn = "";
        try
        {
            // 中文转码
            rtn = java.net.URLDecoder.decode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return rtn;
    }
}
