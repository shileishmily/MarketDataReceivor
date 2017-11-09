package com.youguu.market.sz.data;

public enum MessageEnum {
    市场实时状态(390019, "市场实时状态"),
    证券实时状态(390013,"证券实时状态"),
    公告(390012,"公告"),
    集中竞价交易业务行情快照(300111,"集中竞价交易业务行情快照"),
    盘后定价交易业务行情快照(300611,"盘后定价交易业务行情快照"),
    指数行情快照(309011,"指数行情快照"),
    成交量统计指标行情快照(309111,"成交量统计指标行情快照"),
    港股实时行情快照(306311,"港股实时行情快照"),
    集中竞价业务逐笔委托行情(300192,"集中竞价业务逐笔委托行情"),
    协议交易业务逐笔委托行情(300592,"协议交易业务逐笔委托行情"),
    转融通证券出借业务逐笔委托行情(300792,"转融通证券出借业务逐笔委托行情"),
    频道心跳(390095,"频道心跳"),
    重传消息(390094,"重传消息"),
    用户信息报告消息(390093,"用户信息报告消息"),
    快照行情频道统计(390090,"快照行情频道统计"),
    业务拒绝消息(8,"业务拒绝消息"),
    登录消息(1,"登录消息"),
    注销消息(2,"注销消息"),
    心跳消息(3,"心跳消息");

    int id;
    String name;
    
    MessageEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public static String findById(int id) {
        for(MessageEnum messageEnum : MessageEnum.values()){
            if(messageEnum.getId() == id){
                return messageEnum.name;
            }

        }
        return String.valueOf(id);
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        String name = MessageEnum.findById(390019);
        System.out.println(name);
    }
}