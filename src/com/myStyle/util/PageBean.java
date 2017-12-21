package com.myStyle.util;

public class PageBean
{

    private int pageNo; // --当前页号

    private int totalRecords; // --查询总记录数

    private int pageSize = 10; // --页面显示记录数

    private int totalPages = 1; // --总页数

    private int startRecord; // --起始记录数

    private int endRecord; // --末记录

    private int firstPage = 1; // --首页

    private int lastPage; // --末页

    private int nextPage; // --下一页

    private int prevPage; // --上一页

    public PageBean()
    {
        super();
    }

    /**
     * @param pageNo
     *            当前页号
     * @param totalRecords
     *            总记录数
     * @param url
     *            路径
     * @param pageSize
     *            页面显示记录数(当输入pageSize=0 默认为10 )
     * @param navSize
     *            页面显示也是 (当输入navSize=0 默认为10 )
     */
    public PageBean(int pageNo, int pageSize)
    {
        this.pageNo = pageNo;
        if (pageSize != 0)
        {
            this.pageSize = pageSize;
        }
    }

    /**
     * PAGENO
     * 
     * @return INT
     */
    public int getPageNo()
    {
        // 如果当前页码大于总页数 则显示最后一页的数据
        if (pageNo > totalPages)
        {
            return totalPages;
        }
        return pageNo;

    }

    /**
     * PAGENO
     * 
     * @param pageNo
     */
    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    /**
     * TOTALRECORDS
     * 
     * @return INT
     */
    public int getTotalRecords()
    {
        return totalRecords;
    }

    /**
     * TOTALRECORDS
     * 
     * @param totalRecords
     */
    public void setTotalRecords(int totalRecords)
    {
        this.totalRecords = totalRecords;

        this.totalPages = this.totalRecords / this.pageSize;
        if (this.totalRecords % this.pageSize > 0)
        {
            this.totalPages = this.totalPages + 1;
        }

        // 如果当前页面大于总页数
        if (this.pageNo >= this.totalPages)
        {
            this.pageNo = this.totalPages;
        }

        this.startRecord = (this.pageNo - 1) * this.pageSize;
        this.endRecord = Math.min(this.startRecord + this.pageSize,
                this.totalRecords);
        this.lastPage = this.totalPages;
        this.nextPage = Math.min(this.pageNo + 1, this.lastPage);
        this.prevPage = Math.max(1, this.pageNo - 1);
    }

    /**
     * PAGESIZE
     * 
     * @return
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * PAGESIZE
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * TOATALPAGES
     * 
     * @return
     */
    public int getTotalPages()
    {
        totalPages = totalPages < 1 ? 1 : totalPages;
        return totalPages;
    }

    /**
     * TOTALPAGES
     * 
     * @param totalPages
     */
    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    /**
     * STARTPAGE
     * 
     * @return
     */
    public int getStartRecord()
    {
        return startRecord;
    }

    /**
     * STARTRECORD
     * 
     * @param startRecord
     */
    public void setStartRecord(int startRecord)
    {
        this.startRecord = startRecord;
    }

    /**
     * ENDRECORD
     * 
     * @return INT
     */
    public int getEndRecord()
    {
        return endRecord;
    }

    /**
     * ENDRECORD
     * 
     * @param endRecord
     */
    public void setEndRecord(int endRecord)
    {
        this.endRecord = endRecord;
    }

    /**
     * FIRSTPAGE
     * 
     * @return INT
     */
    public int getFirstPage()
    {
        return firstPage;
    }

    /**
     * FIRSTPAGE
     * 
     * @param firstPage
     */
    public void setFirstPage(int firstPage)
    {
        this.firstPage = firstPage;
    }

    /**
     * LASTPAGE
     * 
     * @return
     */
    public int getLastPage()
    {
        return lastPage;
    }

    /**
     * LASTPAGE
     * 
     * @param lastPage
     */
    public void setLastPage(int lastPage)
    {
        this.lastPage = lastPage;
    }

    /**
     * NEXTPAGE
     * 
     * @return
     */
    public int getNextPage()
    {
        return nextPage;
    }

    /**
     * NEXTPAGE
     * 
     * @param nextPage
     */
    public void setNextPage(int nextPage)
    {
        this.nextPage = nextPage;
    }

    /**
     * PREVPAGE
     * 
     * @return INT
     */
    public int getPrevPage()
    {
        return prevPage;
    }

    /**
     * SETPREVPAGE
     * 
     * @param prevPage
     */
    public void setPrevPage(int prevPage)
    {
        this.prevPage = prevPage;
    }
}
