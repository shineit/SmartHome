//
//  THud.m
//  Taxation
//
//  Created by Seven on 15-2-8.
//  Copyright (c) 2015年 Allgateways. All rights reserved.
//

#import "THud.h"
#import <MBProgressHUD/MBProgressHUD.h>
#import "UIFont+AppFont.h"

@interface THud ()

@property (nonatomic, strong) MBProgressHUD *hud;

@end

@implementation THud

+(THud *)sharedInstance{
    static THud *instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        instance = [[THud alloc] init];
    });
    return instance;
}

-(id)initWithView:(UIView *)view{
    self = [super init];
    if (self) {
        _hud = [[MBProgressHUD alloc] initWithView:view];
        _hud.labelFont = [UIFont appFontWithSize:12];
        _hud.mode = MBProgressHUDModeText;
        [view addSubview:_hud];
        
    }
    return self;
}

-(void)disPlayMessage:(NSString *)message{
    _hud.labelText = message;
    [_hud show:YES];
    [_hud hide:YES afterDelay:2.0];
    
}



@end
