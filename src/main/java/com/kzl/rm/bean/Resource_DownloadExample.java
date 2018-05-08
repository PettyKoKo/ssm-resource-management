package com.kzl.rm.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Resource_DownloadExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public Resource_DownloadExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andResourceIdIsNull() {
            addCriterion("resource_id is null");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNotNull() {
            addCriterion("resource_id is not null");
            return (Criteria) this;
        }

        public Criteria andResourceIdEqualTo(Long value) {
            addCriterion("resource_id =", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotEqualTo(Long value) {
            addCriterion("resource_id <>", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThan(Long value) {
            addCriterion("resource_id >", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("resource_id >=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThan(Long value) {
            addCriterion("resource_id <", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThanOrEqualTo(Long value) {
            addCriterion("resource_id <=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdIn(List<Long> values) {
            addCriterion("resource_id in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotIn(List<Long> values) {
            addCriterion("resource_id not in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdBetween(Long value1, Long value2) {
            addCriterion("resource_id between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotBetween(Long value1, Long value2) {
            addCriterion("resource_id not between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceNameIsNull() {
            addCriterion("resource_name is null");
            return (Criteria) this;
        }

        public Criteria andResourceNameIsNotNull() {
            addCriterion("resource_name is not null");
            return (Criteria) this;
        }

        public Criteria andResourceNameEqualTo(String value) {
            addCriterion("resource_name =", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameNotEqualTo(String value) {
            addCriterion("resource_name <>", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameGreaterThan(String value) {
            addCriterion("resource_name >", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("resource_name >=", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameLessThan(String value) {
            addCriterion("resource_name <", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameLessThanOrEqualTo(String value) {
            addCriterion("resource_name <=", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameLike(String value) {
            addCriterion("resource_name like", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameNotLike(String value) {
            addCriterion("resource_name not like", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameIn(List<String> values) {
            addCriterion("resource_name in", values, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameNotIn(List<String> values) {
            addCriterion("resource_name not in", values, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameBetween(String value1, String value2) {
            addCriterion("resource_name between", value1, value2, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameNotBetween(String value1, String value2) {
            addCriterion("resource_name not between", value1, value2, "resourceName");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountIsNull() {
            addCriterion("downloader_account is null");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountIsNotNull() {
            addCriterion("downloader_account is not null");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountEqualTo(String value) {
            addCriterion("downloader_account =", value, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountNotEqualTo(String value) {
            addCriterion("downloader_account <>", value, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountGreaterThan(String value) {
            addCriterion("downloader_account >", value, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountGreaterThanOrEqualTo(String value) {
            addCriterion("downloader_account >=", value, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountLessThan(String value) {
            addCriterion("downloader_account <", value, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountLessThanOrEqualTo(String value) {
            addCriterion("downloader_account <=", value, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountLike(String value) {
            addCriterion("downloader_account like", value, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountNotLike(String value) {
            addCriterion("downloader_account not like", value, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountIn(List<String> values) {
            addCriterion("downloader_account in", values, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountNotIn(List<String> values) {
            addCriterion("downloader_account not in", values, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountBetween(String value1, String value2) {
            addCriterion("downloader_account between", value1, value2, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloaderAccountNotBetween(String value1, String value2) {
            addCriterion("downloader_account not between", value1, value2, "downloaderAccount");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeIsNull() {
            addCriterion("download_time is null");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeIsNotNull() {
            addCriterion("download_time is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeEqualTo(Date value) {
            addCriterion("download_time =", value, "downloadTime");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeNotEqualTo(Date value) {
            addCriterion("download_time <>", value, "downloadTime");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeGreaterThan(Date value) {
            addCriterion("download_time >", value, "downloadTime");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("download_time >=", value, "downloadTime");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeLessThan(Date value) {
            addCriterion("download_time <", value, "downloadTime");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeLessThanOrEqualTo(Date value) {
            addCriterion("download_time <=", value, "downloadTime");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeIn(List<Date> values) {
            addCriterion("download_time in", values, "downloadTime");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeNotIn(List<Date> values) {
            addCriterion("download_time not in", values, "downloadTime");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeBetween(Date value1, Date value2) {
            addCriterion("download_time between", value1, value2, "downloadTime");
            return (Criteria) this;
        }

        public Criteria andDownloadTimeNotBetween(Date value1, Date value2) {
            addCriterion("download_time not between", value1, value2, "downloadTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}