//
//  FEProduct.h
//  SmartHome
//
//  Created by Seven on 15-4-2.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "SSObject.h"

@interface FEProduct : SSObject

//private static final long serialVersionUID = 1L;
//
//private int productID;			//产品ID，自增长，主键
//private String name;			//产品名称
//private String desp;			//产品描述
//private float price;			//产品价格
//private String picLabel;		//产品标识图片（主图）路径
//private String picDetail1;		//产品图片1
//private String picDetail2;		//产品图片2
//private String picDetail3;		//产品图片3
//private int type=0;				//产品面向终端类型，0-公共；1-家庭端，2-消防端

@property (nonatomic, strong) NSNumber *serialVersionUID;
@property (nonatomic, strong) NSString *name;
@property (nonatomic, strong) NSString *desp;
@property (nonatomic, strong) NSNumber *price;
@property (nonatomic, strong) NSString *picLabel;
@property (nonatomic, strong) NSString *picDetail1;
@property (nonatomic, strong) NSString *picDetail2;
@property (nonatomic, strong) NSString *picDetail3;
@property (nonatomic, strong) NSNumber *type;

@end
