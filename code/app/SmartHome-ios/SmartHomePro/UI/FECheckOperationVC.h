//
//  FECheckOperationVC.h
//  SmartHome
//
//  Created by Seven on 15-4-8.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"

@class FECheckItem;
@class FECompany;

@interface FECheckOperationVC : FECommonViewController
@property (nonatomic, strong) FECompany *company;
@property (nonatomic, strong) FECheckItem *checkItem;

@end
