//
//  FEMarkAddPopView.m
//  SmartHome
//
//  Created by Seven on 14-12-29.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEMarkAddPopView.h"

@implementation FEMarkAddPopView

-(id)initWithTitle:(NSString *)title Titles:(NSArray *)titlearray{
    self = [super init];
    if (self) {
        self.containerView = [self creatInputView:title];
        [self setButtonTitles:titlearray];
        [self setUseMotionEffects:NO];
    }
    return self;
}

-(UIView *)creatInputView:(NSString *)displayname{
    UIView *view = [[UIView alloc]initWithFrame:CGRectMake(0, 0, 290, 105)];
    UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(20, 15, 250, 20)];
    label.backgroundColor = [UIColor clearColor];
    label.textColor = FEColor(51, 51, 51, 1);
    label.font = [UIFont systemFontOfSize:16];
    label.text = displayname;
    [view addSubview:label];
    
    UITextField *input = [[UITextField alloc] initWithFrame:CGRectMake(15, 50, 260, 40)];
    input.contentVerticalAlignment = UIControlContentVerticalAlignmentCenter;
    input.clearButtonMode = UITextFieldViewModeWhileEditing;
    input.tag = 2;
    input.leftView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 7, 1)];
    input.leftViewMode = UITextFieldViewModeAlways;
    input.layer.cornerRadius = 5;
    input.layer.borderWidth = 0.5;
    input.layer.masksToBounds = YES;
    input.layer.borderColor = FEColor(164, 164, 164, 1).CGColor;
    [view addSubview:input];
    self.textField = input;
    [self performSelector:@selector(becomeFirstResponder:) withObject:nil afterDelay:0.2];
    return view;
}

-(void)becomeFirstResponder:(id)sender{
    [[self.containerView viewWithTag:2] becomeFirstResponder];
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
