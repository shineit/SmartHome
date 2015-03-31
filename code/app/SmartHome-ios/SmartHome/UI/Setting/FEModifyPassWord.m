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
#import "FECommonDefine.h"


@interface FEModifyPassWord ()<UITextFieldDelegate>

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
        self.title = kString(@"PSW_MODIFY_PASSWORD");
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
    CGFloat xoffset = 10;
    
    UITapGestureRecognizer *gest = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard:)];
    [self.view addGestureRecognizer:gest];
    self.view.userInteractionEnabled = YES;
    
    UIView *content = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 180)];
    content.userInteractionEnabled = YES;
    content.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:content];
    
    //旧密码
    FELabel *oldpsw = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, yoffset + 5, 80, 20)];
    oldpsw.text = kString(@"PSW_OLD_PASSWORD");
    [content addSubview:oldpsw];
    
    UITextField *oldpswText = [[UITextField alloc] initWithFrame:CGRectMake(oldpsw.frame.origin.x + oldpsw.frame.size.width + 10, yoffset, 200, 30)];
    oldpswText.secureTextEntry = YES;
    oldpswText.delegate = self;
    oldpswText.borderStyle = UITextBorderStyleRoundedRect;
    oldpswText.returnKeyType = UIReturnKeyNext;
    [content addSubview:oldpswText];
    self.oldpswText = oldpswText;
    
    //新密码
    FELabel *newpsw = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, oldpswText.frame.origin.y + oldpswText.bounds.size.height + 10 + 5, 80, 20)];
    newpsw.text = kString(@"PSW_NEW_PSW");
    [content addSubview:newpsw];
    
    UITextField *newpswText = [[UITextField alloc] initWithFrame:CGRectMake(newpsw.frame.origin.x + newpsw.frame.size.width + 10, newpsw.frame.origin.y, 200, 30)];
    newpswText.delegate = self;
    newpswText.borderStyle = UITextBorderStyleRoundedRect;
    newpswText.returnKeyType = UIReturnKeyNext;
    newpswText.secureTextEntry = YES;
    [content addSubview:newpswText];
    self.newpswText = newpswText;
    
    //校验密码
    FELabel *confirmpsw = [[FELabel alloc] initWithFrame:CGRectMake(xoffset, newpswText.frame.origin.y + newpswText.bounds.size.height + 10 + 5, 80, 20)];
    confirmpsw.text = kString(@"PSW_CONFIRM");
    [content addSubview:confirmpsw];
    
    UITextField *confirmpswText = [[UITextField alloc] initWithFrame:CGRectMake(confirmpsw.frame.origin.x + confirmpsw.frame.size.width + 10, confirmpsw.frame.origin.y, 200, 30)];
    confirmpswText.delegate = self;
    confirmpswText.secureTextEntry = YES;
    confirmpswText.returnKeyType = UIReturnKeyDone;
    confirmpswText.borderStyle = UITextBorderStyleRoundedRect;
    [content addSubview:confirmpswText];
    self.confirmpswText = confirmpswText;
    
    FEButton *submit = [FEButton buttonWithType:UIButtonTypeCustom];
    submit.frame = CGRectMake(20, content.frame.origin.y + content.bounds.size.height + 40, self.view.bounds.size.width - 40, 40);
    [submit addTarget:self action:@selector(modify:) forControlEvents:UIControlEventTouchUpInside];
    [submit setTitle:kString(@"PSW_MODIFY_PASSWORD") forState:UIControlStateNormal];
    [self.view addSubview:submit];
    
}

-(void)modify:(UIButton *)btn{
    CDUser *user = FELoginUser;
//    [self screenOffset:0];
    [self hideKeyboard:nil];
    if ([user.password isEqualToString:[self.oldpswText.text MD5]]) {
        if ([self.newpswText.text isEqualToString:self.confirmpswText.text]) {
            [self displayHUD:kString(@"LOADING...")];
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

#pragma mark - UITextFieldDelegate
-(BOOL)textFieldShouldReturn:(UITextField *)textField{
    [textField resignFirstResponder];
    if (textField == self.oldpswText) {
        [self.newpswText becomeFirstResponder];
    }else if(textField == self.newpswText){
        [self.confirmpswText becomeFirstResponder];
    }else if(textField == self.confirmpswText){
        [self modify:nil];
    }
    return YES;
}

-(BOOL)textFieldShouldBeginEditing:(UITextField *)textField{
    if (textField == self.oldpswText) {
        [self screenOffset:SYSTEM_VERSION_UP7?64:0];
    }else if(textField == self.newpswText){
        [self screenOffset:SYSTEM_VERSION_UP7?64:0];
    }else if(textField == self.confirmpswText){
        [self screenOffset:[UIDevice is4Inch]?SYSTEM_VERSION_UP7?64:0:SYSTEM_VERSION_UP7?-20:-40];
    }
    return YES;
}

-(void)hideKeyboard:(UIGestureRecognizer *)ges{
    [self screenOffset:SYSTEM_VERSION_UP7?64:0];
    [self.oldpswText resignFirstResponder];
    [self.newpswText resignFirstResponder];
    [self.confirmpswText resignFirstResponder];
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
