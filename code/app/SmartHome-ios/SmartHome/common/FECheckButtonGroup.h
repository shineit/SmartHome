//
//  FECheckButtonGroup.h
//  SmartHome
//
//  Created by Seven on 14-11-2.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import <Foundation/Foundation.h>

@class FECheckButton;

@interface FECheckButtonGroup : NSObject

@property (nonatomic, assign) NSInteger checkedindex;

-(void)addButton:(FECheckButton *)checkbutton;
-(void)removeButton:(FECheckButton *)checkbutton;
-(void)checkButton:(FECheckButton *)checkButon;

@end
