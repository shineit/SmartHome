//
//  FECompany.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FECompany : SSObject

//private static final long serialVersionUID = 1L;
//
//private int companyID;					//单位编号，自增长，主键
//private String companyName; 			//单位名称，可变需控制长度
//private String applyName;				//使用名称，可变需控制长度
//private String companyAddr;				//单位地址
//private String companyType;				//单位类型
//private String companyPhone;			//单位电话
//private float buildingArea;				//建筑面积
//
//private String legalOfficer;			//法人名字
//private String officerPhone;			//法人电话
//private String fireManager;				//管理人名字
//private String managerPhone; 			//管理人电话
//private String fireDuty;				//责任人名字
//private String dutyPhone; 				//责任人电话
//private String extendInfo;				//扩展字段，其他信息补充
//
//private String fireRisk;                //火灾危险性

@property (nonatomic, strong) NSNumber *serialVersionUID;
@property (nonatomic, strong) NSNumber *companyID;
@property (nonatomic, strong) NSString *applyName;
@property (nonatomic, strong) NSString *companyAddr;
@property (nonatomic, strong) NSString *companyType;
@property (nonatomic, strong) NSString *companyPhone;
@property (nonatomic, strong) NSNumber *buildingArea;
@property (nonatomic, strong) NSString *legalOfficer;
@property (nonatomic, strong) NSString *officerPhone;
@property (nonatomic, strong) NSString *fireManager;
@property (nonatomic, strong) NSString *managerPhone;
@property (nonatomic, strong) NSString *fireDuty;
@property (nonatomic, strong) NSString *dutyPhone;
@property (nonatomic, strong) NSString *extendInfo;
@property (nonatomic, strong) NSString *fireRisk;


@end
