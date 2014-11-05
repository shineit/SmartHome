//
//  FEModifyPassWord.m
//  SmartHome
//
//  Created by Seven on 14-10-23.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEModifyPassWord.h"
#import "FEWebServiceManager.h"
#import "FEModifyPasswordRequest.h"
#import "AppDelegate.h"
#import "FECoreDataHandler.h"
#import "CDUser.h"
#import "FEResult.h"
#import "FEBaseResponse.h"


@interface FEModifyPassWord ()

@property (nonatomic, strong) UITextField *oldpswText;
@property (nonatomic, strong) UITextField *newpswText;
@property (nonatomic, strong) UITextField *confirmpswText;
@end

@implementation FEModifyPassWord

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"PSW_MODIFY_PASSWORD");
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
    FELabel *oldpsw = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, yoffset + 5, 80, 20)];
    oldpsw.text = FEString(@"PSW_OLD_PASSWORD");
    [self.view addSubview:oldpsw];
    
    UITextField *oldpswText = [[UITextField alloc] initWithFrame:CGRectMake(oldpsw.frame.origin.x + oldpsw.frame.size.width + 10, yoffset, 200, 30)];
    oldpswText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:oldpswText];
    self.oldpswText = oldpswText;
    
    //新密码
    FELabel *newpsw = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, oldpswText.frame.origin.y + oldpswText.bounds.size.height + 10 + 5, 80, 20)];
    newpsw.text = FEString(@"PSW_NEW_PSW");
    [self.view addSubview:newpsw];
    
    UITextField *newpswText = [[UITextField alloc] initWithFrame:CGRectMake(newpsw.frame.origin.x + newpsw.frame.size.width + 10, newpsw.frame.origin.y, 200, 30)];
    newpswText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:newpswText];
    self.newpswText = newpswText;
    
    //校验密码
    FELabel *confirmpsw = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, newpswText.frame.origin.y + newpswText.bounds.size.height + 10 + 5, 80, 20)];
    confirmpsw.text = FEString(@"PSW_CONFIRM");
    [self.view addSubview:confirmpsw];
    
    UITextField *confirmpswText = [[UITextField alloc] initWithFrame:CGRectMake(confirmpsw.frame.origin.x + confirmpsw.frame.size.width + 10, confirmpsw.frame.origin.y, 200, 30)];
    confirmpswText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:confirmpswText];
    self.confirmpswText = confirmpswText;
    
    FEButton *submit = [FEButton buttonWithType:UIButtonTypeCustom];
    submit.frame = CGRectMake(80, confirmpswText.frame.origin.y + confirmpswText.frame.size.height + 40, 160, 30);
    [submit addTarget:self action:@selector(modify:) forControlEvents:UIControlEventTouchUpInside];
    [submit setTitle:FEString(@"PSW_MODIFY_PASSWORD") forState:UIControlStateNormal];
    [self.view addSubview:submit];
    
}

-(void)modify:(UIButton *)btn{
    CDUser *user = FELoginUser;
    
    if ([user.password isEqualToString:[self.oldpswText.text MD5]]) {
        if ([self.newpswText.text isEqualToString:self.confirmpswText.text]) {
            [self displayHUD:FEString(@"LOADING...")];
            FEModifyPasswordRequest *mdate = [[FEModifyPasswordRequest alloc] initWithUname:user.username oldPwd:user.password newPwd:[self.newpswText.text MD5]];
            __weak typeof(self) weakself = self;
            [[FEWebServiceManager sharedInstance] modifyPassword:mdate response:^(NSError *error, FEBaseResponse *response) {
                [self hideHUD:YES];
                if (!error && response.result.errorCode.integerValue == 0) {
                    [weakself.navigationController popViewControllerAnimated:YES];
                }
            }];
        }
    }
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
