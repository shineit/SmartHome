//
//  FEServiceRequestVC.h
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"

@class FEOrder;

typedef enum : NSUInteger {
    SHOW_SERVICE,
    EDIT_SERVICE,
} SERVICE_TYPE;

@interface FEServiceRequestVC : FECommonViewController

@property (nonatomic, assign) SERVICE_TYPE type;
@property (nonatomic, assign) FEOrder *order;

-(void)disableAllItem;

@end
