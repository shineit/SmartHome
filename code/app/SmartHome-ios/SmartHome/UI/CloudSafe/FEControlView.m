//
//  FEControlView.m
//  SmartHome
//
//  Created by Seven on 14-10-22.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEControlView.h"

@implementation FEControlView

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
    
    UIButton *open = [UIButton buttonWithType:UIButtonTypeCustom];
    [open setTitle:FEString(@"ALL_OPEN") forState:UIControlStateNormal];
    [open setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    open.frame = CGRectMake(20, 5, 100, 30);
    [self addSubview:open];
    
    UIButton *close = [UIButton buttonWithType:UIButtonTypeCustom];
    [close setTitle:FEString(@"ALL_OPEN") forState:UIControlStateNormal];
    [close setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    close.frame = CGRectMake(200, 5, 100, 30);
    [self addSubview:close];
    
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
