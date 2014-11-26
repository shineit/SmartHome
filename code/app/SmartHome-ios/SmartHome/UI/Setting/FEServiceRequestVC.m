//
//  FEServiceRequestVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEServiceRequestVC.h"
#import "FEWebServiceManager.h"
#import "FECheckButton.h"
#import "FECheckButtonGroup.h"
#import "AppDelegate.h"
#import "CDUser.h"
#import "FECoreDataHandler.h"
#import "FEResult.h"
#import "FEOrderSetResponse.h"
#import "FEOrder.h"

@interface FEServiceRequestVC (){
    NSArray *_ordertype;
}

@property (nonatomic, strong) FELabel *number;
@property (nonatomic, strong) UITextField *titleTextField;
@property (nonatomic, strong) UITextField *contentTextField;
@property (nonatomic, strong) UITextField *contact;
@property (nonatomic, strong) UITextField *phonenumber;
@property (nonatomic, strong) UITextField *address;
@property (nonatomic, strong) UIScrollView *scrollContent;
@property (nonatomic, strong) FECheckButtonGroup *checkGroup;

@end

@implementation FEServiceRequestVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"SERVICE_REQUEST");
        _ordertype = [NSArray arrayWithObjects:@{@"type":@(0),@"name":FEString(@"ORDER_INSTALL")},@{@"type":@(1),@"name":FEString(@"ORDER_REPAIR")}, nil];
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
    
    
    UIScrollView *scrollcontent = [[UIScrollView alloc] initWithFrame:self.view.bounds];
    scrollcontent.userInteractionEnabled = YES;
    scrollcontent.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [self.view addSubview:scrollcontent];
    self.scrollContent = scrollcontent;
    self.scrollContent.contentSize = CGSizeMake(self.view.bounds.size.width, self.scrollContent.bounds.size.height);
    
    UITapGestureRecognizer *tges = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard:)];
    [self.scrollContent addGestureRecognizer:tges];
    
    UIView *content = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.view.bounds.size.width, 350)];
    content.backgroundColor = [UIColor whiteColor];
    [scrollcontent addSubview:content];
    
    //编号
    FELabel *label = [[FELabel alloc] initWithFrame:CGRectMake(10, 15, 80, 20)];
    label.text = FEString(@"ORDER_NUMBER");
    [content addSubview:label];
    
    _number = [[FELabel alloc] initWithFrame:CGRectMake(110, 10, 100, 20)];
    _number.text = @"1234";
    [content addSubview:_number];
    
    //类型
    FELabel *type = [[FELabel alloc] initWithFrame:CGRectMake(10, 65, 60, 20)];
    type.text = FEString(@"ORDER_TYPE");
    [content addSubview:type];
    
    //check group
    _checkGroup = [FECheckButtonGroup new];
    
//    UIButton *repair = [UIButton buttonWithType:UIButtonTypeCustom];
    FECheckButton *repair = [[FECheckButton alloc] initWithFrame:CGRectMake(80, 60, 100, 30)];
    [repair addObserver:self check:@selector(check:)];
    [repair setTitle:_ordertype[0][@"name"]];
    [_checkGroup addButton:repair];
    [content addSubview:repair];
    
    
    FECheckButton *consult = [[FECheckButton alloc] initWithFrame:CGRectMake(200, 60, 100, 30)];
    [consult addObserver:self check:@selector(check:)];
    [consult setTitle:_ordertype[1][@"name"]];
    [_checkGroup addButton:consult];
    [content addSubview:consult];
    
    
    //名称
    FELabel *title = [[FELabel alloc] initWithFrame:CGRectMake(10, 125, 60, 20)];
    title.text = FEString(@"ORDER_THEME");
    [scrollcontent addSubview:title];
    
    _titleTextField = [[UITextField alloc] initWithFrame:CGRectMake(80, 120, 220, 30)];
    _titleTextField.borderStyle = UITextBorderStyleRoundedRect;
    _titleTextField.text = @"test";
    [content addSubview:_titleTextField];
    
    FELabel *contentlabel = [[FELabel alloc] initWithFrame:CGRectMake(10, 165, 60, 20)];
    contentlabel.text = FEString(@"ORDER_CONTENT");
    [content addSubview:contentlabel];
    
    _contentTextField = [[UITextField alloc] initWithFrame:CGRectMake(80, 160, 220, 30)];
    _contentTextField.borderStyle = UITextBorderStyleRoundedRect;
    _contentTextField.text = @"test content!";
    [content addSubview:_contentTextField];
    
    //联系人
    FELabel *contact = [[FELabel alloc] initWithFrame:CGRectMake(10, 235, 70, 20)];
    contact.text = FEString(@"ORDER_CONTACT");
    [content addSubview:contact];
    
    _contact = [[UITextField alloc] initWithFrame:CGRectMake(85, 230, 205, 30)];
    _contact.borderStyle = UITextBorderStyleRoundedRect;
    _contact.text = @"tester";
    [content addSubview:_contact];
    
    //电话
    FELabel *phone = [[FELabel alloc] initWithFrame:CGRectMake(10, 275, 70, 20)];
    phone.text = FEString(@"ORDER_PHONENUMBER");
    [content addSubview:phone];
    
    _phonenumber = [[UITextField alloc] initWithFrame:CGRectMake(85, 270, 205, 30)];
    _phonenumber.borderStyle = UITextBorderStyleRoundedRect;
    _phonenumber.text = @"123456";
    [content addSubview:_phonenumber];
    
    //adress
    FELabel *adress = [[FELabel alloc] initWithFrame:CGRectMake(10, 315, 70, 20)];
    adress.text = FEString(@"ORDER_ADRESS");
    [content addSubview:adress];
    
    _address = [[UITextField alloc] initWithFrame:CGRectMake(85, 310, 205, 30)];
    _address.borderStyle = UITextBorderStyleRoundedRect;
    _address.text = @"test address";
    [content addSubview:_address];
    
    if (self.type == EDIT_SERVICE) {
        //删除按钮
        UIButton *del = [UIButton buttonWithType:UIButtonTypeCustom];
        del.frame = CGRectMake(20, 360, 80, 40);
        [del setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
        [del setTitle:FEString(@"ORDER_DELET") forState:UIControlStateNormal];
        [scrollcontent addSubview:del];
        
        //提交按钮
        FEButton *submit = [FEButton buttonWithType:UIButtonTypeCustom];
        submit.frame = CGRectMake(220, 360, 80, 40);
        [submit setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
        [submit setTitle:FEString(@"ORDER_SUBMIT") forState:UIControlStateNormal];
        [submit addTarget:self action:@selector(submit:) forControlEvents:UIControlEventTouchUpInside];
        [scrollcontent addSubview:submit];
    }else if(self.order){
        _titleTextField.text = self.order.orderName;
        
    }
    
    [self check:repair];
    
    
}

-(void)disableAllItem{
    self.view.userInteractionEnabled = NO;
}

-(void)check:(FECheckButton *)btn{
    [_checkGroup checkButton:btn];
}

-(void)submit:(UIButton *)button{
    FEOrder *order = [[FEOrder alloc] initWithOrderID:[NSString UUID] name:_titleTextField.text type:_ordertype[_checkGroup.checkedindex][@"type"] content:_contentTextField.text creater:FELoginUser.username time:@([[NSDate date] timeIntervalSince1970]) contactname:_contact.text phone:_phonenumber.text address:_address.text status:nil handler:nil handResult:nil handTime:nil];
    FEServiceOrderSetRequest *sdata = [[FEServiceOrderSetRequest alloc] initWithCmd:@"" serviceOrder:order];
    [self hideKeyboard:nil];
    [self displayHUD:FEString(@"LOADING...")];
    __weak typeof(self) weakself = self;
    [[FEWebServiceManager sharedInstance] orederSet:sdata response:^(NSError *error, FEOrderSetResponse *response) {
        [weakself hideHUD:YES];
        NSLog(@"set order %@",response);
        if (!error && response.result.errorCode.integerValue == 0){
            [weakself.navigationController popViewControllerAnimated:YES];
        }
    }];
}

-(void)keyboardWillShow:(CGRect)newRect duration:(NSTimeInterval)duration{
    [UIView animateWithDuration:duration animations:^{
        CGRect frame = self.view.bounds;
        frame.size.height -= newRect.size.height;
        self.scrollContent.frame = frame;
    }];
}

-(void)keyboardWillHide:(CGRect)newRect duration:(NSTimeInterval)duration{
    [UIView animateWithDuration:duration animations:^{
        self.scrollContent.frame = self.view.bounds;
    }];
}

-(void)hideKeyboard:(UIGestureRecognizer *)ges{
    [_titleTextField resignFirstResponder];
    [_contentTextField resignFirstResponder];
    [_contact resignFirstResponder];
    [_phonenumber resignFirstResponder];
    [_address resignFirstResponder];
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
