//
//  FECheckLog.h
//  SmartHome
//
//  Created by Seven on 15-4-4.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FECheckLog : SSObject

//private long logID;     		//自增长，主键
//private int  companyID; 		//单位编号，
//private String checkItem; 		//项目名称；
//private String checkSys; 		//项目所属系统；
//private int checkResult;		//巡检结果，0-未设置，1-正常，2-异常
//private String abnormalDesp;	//异常描述
//private String abnormalPic; 	//异常图片地址
//private String checker;			//巡查员名字
//private long  checkTime;		//巡检时间
//private String handler;			//处理人名字
//private String handleResult;	//处理结论
//private long handleTime;		//处理时间
//private int status;          	//处理状态，0-未处理；1-已处理

@property (nonatomic, strong) NSNumber *logID;
@property (nonatomic, strong) NSNumber *companyID;
@property (nonatomic, strong) NSString *companyName;
@property (nonatomic, strong) NSString *checkItem;
@property (nonatomic, strong) NSString *checkSys;
@property (nonatomic, strong) NSNumber *checkResult;
@property (nonatomic, strong) NSString *abnormalDesp;
@property (nonatomic, strong) NSString *abnormalPic;
@property (nonatomic, strong) NSString *checker;
@property (nonatomic, strong) NSNumber *checkTime;
@property (nonatomic, strong) NSString *handler;
@property (nonatomic, strong) NSString *handleResult;
@property (nonatomic, strong) NSNumber *handleTime;
@property (nonatomic, strong) NSNumber *status;
//@property (nonatomic, strong) NSNumber *checkItemID;

@end
