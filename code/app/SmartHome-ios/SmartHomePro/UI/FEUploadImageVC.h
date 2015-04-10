//
//  FEUploadImageVC.h
//  SmartHome
//
//  Created by Seven on 15-4-8.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"

@protocol FEUpLoadImageVCDelegate <NSObject>

@optional
-(void)didUpLoadImage:(UIImage *)image withName:(NSString *)imageName;

@end

@interface FEUploadImageVC : FECommonViewController

@property (nonatomic, weak) id<FEUpLoadImageVCDelegate> delegate;

@end
