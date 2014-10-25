//
//  FEDeviceInfoView.m
//  SmartHome
//
//  Created by Seven on 14-10-21.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEDeviceInfoView.h"

@implementation FEDeviceInfoView

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
    
    CGFloat xoffset = 10;
    CGFloat yoffset = 20;
    CGFloat xspace = 10;
    CGFloat yspace = 20;
    CGFloat twidth = 80;
    CGFloat theight = 20;
    CGFloat vwidth = 100;
    CGFloat vheight = 20;
    CGFloat xvoffset = xoffset + twidth + xspace;
    
    
    FELabel *clabel = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, yoffset, twidth, theight)];
    clabel.text = FEString(@"CONTROLPOINT");
    [self addSubview:clabel];
    
    _controlPoint = [[FELabel alloc] initWithFrame:CGRectMake(xvoffset, yoffset, vwidth, vheight)];
    _controlPoint.text = @"021";
    [self addSubview:_controlPoint];
    
    FELabel *dNumber = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, yoffset + theight + yspace, twidth, theight)];
    dNumber.text = FEString(@"DEVICENUMBER");
    [self addSubview:dNumber];
    
    _deviceNumber = [[FELabel alloc] initWithFrame:CGRectMake(xvoffset, yoffset + vheight + yspace, vwidth, vheight)];
    _deviceNumber.text = @"021";
    [self addSubview:_deviceNumber];
    
    FELabel *dtype = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, yoffset + 2 * theight + 2 * yspace, twidth, theight)];
    dtype.text = FEString(@"DEVICETYPE");
    [self addSubview:dtype];
    
    _deviceType = [[FELabel alloc] initWithFrame:CGRectMake(xvoffset, yoffset + 2 * vheight + 2 * yspace, vwidth, vheight)];
    _deviceType.text = @"烟雾报警器";
    [self addSubview:_deviceType];
    
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
