//
//  FEMarkAddPopView.h
//  SmartHome
//
//  Created by Seven on 14-12-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "CustomIOS7AlertView.h"

@interface FEMarkAddPopView : CustomIOS7AlertView

@property (nonatomic, strong) UITextField *textField;

-(id)initWithTitle:(NSString *)title Titles:(NSArray *)titlearray;

@end
