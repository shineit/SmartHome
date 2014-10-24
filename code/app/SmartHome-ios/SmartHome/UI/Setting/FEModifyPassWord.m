//
//  FEModifyPassWord.m
//  SmartHome
//
//  Created by Seven on 14-10-23.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEModifyPassWord.h"

@interface FEModifyPassWord ()

@end

@implementation FEModifyPassWord

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"MOD_PASSWORD");
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
}

-(void)initUI{
    
    CGFloat yoffset = 30;
    CGFloat xoffset = 20;
    
    //旧密码
    FELabel *oldpsw = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, yoffset, 80, 20)];
    oldpsw.text = FEString(@"OLD_PASSWORD");
    [self.view addSubview:oldpsw];
    
    UITextField *oldpswText = [[UITextField alloc] initWithFrame:CGRectMake(oldpsw.frame.origin.x + oldpsw.frame.size.width + 10, yoffset, 200, 30)];
    oldpswText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:oldpswText];
    
    //新密码
    FELabel *newpsw = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, oldpswText.frame.origin.y + oldpswText.bounds.size.height + 10, 80, 20)];
    newpsw.text = FEString(@"NEW_PSW");
    [self.view addSubview:newpsw];
    
    UITextField *newpswText = [[UITextField alloc] initWithFrame:CGRectMake(newpsw.frame.origin.x + newpsw.frame.size.width + 10, newpsw.frame.origin.y, 200, 30)];
    newpswText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:newpswText];
    
    //校验密码
    FELabel *confirmpsw = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, newpswText.frame.origin.y + newpswText.bounds.size.height + 10, 80, 20)];
    confirmpsw.text = FEString(@"CONFIRM");
    [self.view addSubview:confirmpsw];
    
    UITextField *confirmpswText = [[UITextField alloc] initWithFrame:CGRectMake(confirmpsw.frame.origin.x + confirmpsw.frame.size.width + 10, confirmpsw.frame.origin.y, 200, 30)];
    confirmpswText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:confirmpswText];
    
    UIButton *submit = [UIButton buttonWithType:UIButtonTypeCustom];
    submit.frame = CGRectMake(80, confirmpswText.frame.origin.y + confirmpswText.frame.size.height + 40, 160, 30);
    [submit setTitle:FEString(@"MODIFY_PASSWORD") forState:UIControlStateNormal];
    [self.view addSubview:submit];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
