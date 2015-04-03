//
//  FESensorPlan.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FESensorPlan : SSObject
//private int planID; 	//自增长，主键
//private int buildingID; //楼编号
//private String floor;	//
//private String name;
//private String desp;
//private String picPath; //文件路径

@property (nonatomic, strong, readonly) NSNumber *planID;
@property (nonatomic, strong) NSNumber *buildingID;
@property (nonatomic, strong) NSString *floor;
@property (nonatomic, strong) NSString *name;
@property (nonatomic, strong) NSString *desp;
@property (nonatomic, strong) NSString *picPath;


@end
