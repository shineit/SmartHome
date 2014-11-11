//
//  FENewsDetailVC.h
//  SmartHome
//
//  Created by Seven on 14-11-10.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"
@class FENews;

@interface FENewsDetailVC : FECommonViewController

@property (nonatomic, strong, readonly) FENews *news;

-(id)initWithNews:(FENews *)news;

@end
