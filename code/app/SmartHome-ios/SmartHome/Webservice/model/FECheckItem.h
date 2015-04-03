//
//  FECheckItem.h
//  SmartHome
//
//  Created by Seven on 15-4-3.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FECheckItem : SSObject
//private int itemID;			//项目编号，自增长；
//private String itemName;	//项目名称
//private String itemSys;		//项目所属系统

@property (nonatomic, strong) NSNumber *itemID;
@property (nonatomic, strong) NSString *itemName;
@property (nonatomic, strong) NSString *itemSys;

@end
