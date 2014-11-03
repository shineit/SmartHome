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

@interface FEServiceRequestVC ()

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
    
    self.view.backgroundColor = [UIColor whiteColor];
    
    UIScrollView *scrollcontent = [[UIScrollView alloc] initWithFrame:self.view.bounds];
    scrollcontent.userInteractionEnabled = YES;
    scrollcontent.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [self.view addSubview:scrollcontent];
    self.scrollContent = scrollcontent;
    self.scrollContent.contentSize = CGSizeMake(self.view.bounds.size.width, self.scrollContent.bounds.size.height);
    
    UITapGestureRecognizer *tges = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(hideKeyboard:)];
    [self.scrollContent addGestureRecognizer:tges];
    
    //编号
    FELabel *label = [[FELabel alloc] initWithFrame:CGRectMake(10, 10, 80, 20)];
    label.text = FEString(@"ORDER_NUMBER");
    [scrollcontent addSubview:label];
    
    _number = [[FELabel alloc] initWithFrame:CGRectMake(110, 10, 100, 20)];
    _number.text = @"1234";
    [scrollcontent addSubview:_number];
    
    //类型
    UILabel *type = [[FELabel alloc] initWithFrame:CGRectMake(10, 60, 60, 20)];
    type.text = FEString(@"ORDER_TYPE");
    [scrollcontent addSubview:type];
    
    //check group
    _checkGroup = [FECheckButtonGroup new];
    
//    UIButton *repair = [UIButton buttonWithType:UIButtonTypeCustom];
    FECheckButton *repair = [[FECheckButton alloc] initWithFrame:CGRectMake(80, 60, 80, 30)];
    [repair addObserver:self check:@selector(check:)];
    [repair setTitle:FEString(@"ORDER_REPAIR")];
    [_checkGroup addButton:repair];
    [scrollcontent addSubview:repair];
    
    
    FECheckButton *consult = [[FECheckButton alloc] initWithFrame:CGRectMake(200, 60, 80, 30)];
    [consult addObserver:self check:@selector(check:)];
    [consult setTitle:FEString(@"ORDER_CONSULT")];
    [_checkGroup addButton:consult];
    [scrollcontent addSubview:consult];
    
    
    //名称
    FELabel *title = [[FELabel alloc] initWithFrame:CGRectMake(10, 120, 60, 20)];
    title.text = FEString(@"ORDER_THEME");
    [scrollcontent addSubview:title];
    
    _titleTextField = [[UITextField alloc] initWithFrame:CGRectMake(80, 120, 220, 30)];
    _titleTextField.borderStyle = UITextBorderStyleRoundedRect;
    [scrollcontent addSubview:_titleTextField];
    
    FELabel *content = [[FELabel alloc] initWithFrame:CGRectMake(10, 160, 60, 20)];
    content.text = FEString(@"ORDER_CONTENT");
    [scrollcontent addSubview:content];
    
    _contentTextField = [[UITextField alloc] initWithFrame:CGRectMake(80, 160, 220, 30)];
    _contentTextField.borderStyle = UITextBorderStyleRoundedRect;
    [scrollcontent addSubview:_contentTextField];
    
    //联系人
    FELabel *contact = [[FELabel alloc] initWithFrame:CGRectMake(10, 230, 80, 20)];
    contact.text = FEString(@"ORDER_CONTACT");
    [scrollcontent addSubview:contact];
    
    _contentTextField = [[UITextField alloc] initWithFrame:CGRectMake(110, 230, 190, 30)];
    _contentTextField.borderStyle = UITextBorderStyleRoundedRect;
    [scrollcontent addSubview:_contentTextField];
    
    //电话
    FELabel *phone = [[FELabel alloc] initWithFrame:CGRectMake(10, 270, 80, 20)];
    phone.text = FEString(@"ORDER_PHONENUMBER");
    [scrollcontent addSubview:phone];
    
    _phonenumber = [[UITextField alloc] initWithFrame:CGRectMake(110, 270, 190, 30)];
    _phonenumber.borderStyle = UITextBorderStyleRoundedRect;
    [scrollcontent addSubview:_phonenumber];
    
    //adress
    FELabel *adress = [[FELabel alloc] initWithFrame:CGRectMake(10, 310, 80, 20)];
    adress.text = FEString(@"ORDER_ADRESS");
    [scrollcontent addSubview:adress];
    
    _address = [[UITextField alloc] initWithFrame:CGRectMake(110, 310, 190, 30)];
    _address.borderStyle = UITextBorderStyleRoundedRect;
    [scrollcontent addSubview:_address];
    
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
    
    
}

-(void)check:(FECheckButton *)btn{
    [_checkGroup checkButton:btn];
}

-(void)submit:(UIButton *)button{
    
    FEOrder *order = [[FEOrder alloc] initWithOrderID:[NSString UUID] name:_titleTextField.text type:@"repair" content:_contentTextField.text creater:FELoginUser.username time:@([[NSDate date] timeIntervalSince1970]) contactname:_contact.text phone:_phonenumber.text address:_address.text status:@(1) handler:nil handResult:nil handTime:nil];
    FEServiceOrderSetRequest *sdata = [[FEServiceOrderSetRequest alloc] initWithCmd:@"test" serviceOrder:order];
    
    [[FEWebServiceManager sharedInstance] orederSet:sdata response:^(NSError *error, FEBaseResponse *response) {
       
        if (error) {
            
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"错误" message:[NSString stringWithFormat:@"%@",error.userInfo] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
            [alert show];
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
//        CGRect frame = self.view.bounds;
//        frame.size.height -= newRect.size.height;
//        self.scrollContent.frame = frame;
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
