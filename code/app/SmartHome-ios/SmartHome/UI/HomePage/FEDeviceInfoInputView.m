//
//  FEDeviceInfoInputView.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDeviceInfoInputView.h"

@implementation FEDeviceInfoInputView

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
        [self setup];
    }
    return self;
}

-(void)setup{
    self.backgroundColor = [UIColor whiteColor];
    FELabel *des = [[FELabel alloc] initWithFrame:CGRectMake(10, 10, 50, 20)];
    des.text = FEString(@"DESCRIPTION");
    [self addSubview:des];
    
    _descriptionTextFeild = [[UITextField alloc] initWithFrame:CGRectMake(60, 10, 200, 30)];
    _descriptionTextFeild.borderStyle = UITextBorderStyleRoundedRect;
    _descriptionTextFeild.backgroundColor = [UIColor clearColor];
    _descriptionTextFeild.placeholder = FEString(@"INPUT_DESCRIPTION");
    [self addSubview:_descriptionTextFeild];
    
    FELabel *el = [[FELabel alloc] initWithFrame:CGRectMake(10, 50, 50, 20)];
    el.text = FEString(@"EARLYWARRING");
    [self addSubview:el];
    
    _earlyValue = [[UITextField alloc] initWithFrame:CGRectMake(60, 50, 200, 30)];
    _earlyValue.borderStyle = UITextBorderStyleRoundedRect;
    _earlyValue.backgroundColor = [UIColor clearColor];
    _earlyValue.placeholder = FEString(@"INPUT_EARLYWARRING_VALUE");
    [self addSubview:_earlyValue];
    
    FELabel *wl = [[FELabel alloc] initWithFrame:CGRectMake(10, 90, 50, 20)];
    wl.text = FEString(@"INPUT_VALUE");
    [self addSubview:wl];
    
    _value = [[UITextField alloc] initWithFrame:CGRectMake(60, 90, 200, 30)];
    _value.borderStyle = UITextBorderStyleRoundedRect;
    _value.backgroundColor = [UIColor clearColor];
    _value.placeholder = FEString(@"INPUT_WARRING_VALUE");
    [self addSubview:_value];
    
    
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end
