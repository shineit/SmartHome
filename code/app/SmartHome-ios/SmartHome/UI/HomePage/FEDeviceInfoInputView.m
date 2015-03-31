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
    FELabel *des = [[FELabel alloc] initWithFrame:CGRectMake(10, 10+5, 65, 20)];
    des.text = kString(@"ALARM_DESCRIPTION");
    [self addSubview:des];
    
    _descriptionTextFeild = [[UITextField alloc] initWithFrame:CGRectMake(80, 10, 200, 30)];
    _descriptionTextFeild.borderStyle = UITextBorderStyleRoundedRect;
    _descriptionTextFeild.backgroundColor = [UIColor clearColor];
    _descriptionTextFeild.placeholder = kString(@"ALARM_INPUT_DESCRIPTION");
    [self addSubview:_descriptionTextFeild];
    
    FELabel *el = [[FELabel alloc] initWithFrame:CGRectMake(10, 50+5, 65, 20)];
    el.text = kString(@"ALARM_EARLYWARRING");
    [self addSubview:el];
    
    _earlyValue = [[UITextField alloc] initWithFrame:CGRectMake(80, 50, 200, 30)];
    _earlyValue.borderStyle = UITextBorderStyleRoundedRect;
    _earlyValue.backgroundColor = [UIColor clearColor];
    _earlyValue.placeholder = kString(@"ALARM_INPUT_EARLYWARRING_VALUE");
    [self addSubview:_earlyValue];
    
    FELabel *wl = [[FELabel alloc] initWithFrame:CGRectMake(10, 90+5, 65, 20)];
    wl.text = kString(@"ALARM_INPUT_VALUE");
    [self addSubview:wl];
    
    _value = [[UITextField alloc] initWithFrame:CGRectMake(80, 90, 200, 30)];
    _value.borderStyle = UITextBorderStyleRoundedRect;
    _value.backgroundColor = [UIColor clearColor];
    _value.placeholder = kString(@"ALARM_INPUT_WARRING_VALUE");
    [self addSubview:_value];
    
    
}


// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
    CGContextRef context = UIGraphicsGetCurrentContext();
    
    CGContextSetLineWidth(context, 0.5);
    
    CGContextSetStrokeColorWithColor(context, [[UIColor lightGrayColor] CGColor]);
    
    CGContextMoveToPoint(context, 0,  self.bounds.size.height);
    
    CGContextAddLineToPoint(context, self.frame.size.width, self.bounds.size.height);
    
    CGContextStrokePath(context);
}


@end
