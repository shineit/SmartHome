//
//  FEServiceRequestVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FEServiceRequestVC.h"

@interface FEServiceRequestVC ()

@property (nonatomic, strong) FELabel *number;
@property (nonatomic, strong) UITextField *titleTextField;
@property (nonatomic, strong) UITextField *contentTextField;
@property (nonatomic, strong) UITextField *contact;
@property (nonatomic, strong) UITextField *phonenumber;
@property (nonatomic, strong) UITextField *address;

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
    
    //编号
    FELabel *label = [[FELabel alloc] initWithFrame:CGRectMake(10, 10, 80, 20)];
    label.text = FEString(@"NUMBER");
    [self.view addSubview:label];
    
    _number = [[FELabel alloc] initWithFrame:CGRectMake(110, 10, 100, 20)];
    _number.text = @"1234";
    [self.view addSubview:_number];
    
    //类型
    UILabel *type = [[FELabel alloc] initWithFrame:CGRectMake(10, 60, 60, 20)];
    type.text = FEString(@"TYPE");
    [self.view addSubview:type];
    
    UIButton *repair = [UIButton buttonWithType:UIButtonTypeCustom];
    repair.frame = CGRectMake(80, 60, 80, 30);
    [repair setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [repair setTitle:FEString(@"REPAIR") forState:UIControlStateNormal];
    [self.view addSubview:repair];
    
    UIButton *consult = [UIButton buttonWithType:UIButtonTypeCustom];
    consult.frame = CGRectMake(200, 60, 80, 30);
    [consult setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [consult setTitle:FEString(@"CONSULT") forState:UIControlStateNormal];
    [self.view addSubview:consult];
    
    //名称
    FELabel *title = [[FELabel alloc] initWithFrame:CGRectMake(10, 120, 60, 20)];
    title.text = FEString(@"THEME");
    [self.view addSubview:title];
    
    _titleTextField = [[UITextField alloc] initWithFrame:CGRectMake(80, 120, 220, 30)];
    _titleTextField.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:_titleTextField];
    
    FELabel *content = [[FELabel alloc] initWithFrame:CGRectMake(10, 160, 60, 20)];
    content.text = FEString(@"CONTENT");
    [self.view addSubview:content];
    
    _contentTextField = [[UITextField alloc] initWithFrame:CGRectMake(80, 160, 220, 30)];
    _contentTextField.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:_contentTextField];
    
    //联系人
    FELabel *contact = [[FELabel alloc] initWithFrame:CGRectMake(10, 230, 80, 20)];
    contact.text = FEString(@"CONTACT");
    [self.view addSubview:contact];
    
    _contentTextField = [[UITextField alloc] initWithFrame:CGRectMake(110, 230, 190, 30)];
    _contentTextField.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:_contentTextField];
    
    //电话
    FELabel *phone = [[FELabel alloc] initWithFrame:CGRectMake(10, 270, 80, 20)];
    phone.text = FEString(@"PHONENUMBER");
    [self.view addSubview:phone];
    
    _phonenumber = [[UITextField alloc] initWithFrame:CGRectMake(110, 270, 190, 30)];
    _phonenumber.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:_phonenumber];
    
    //adress
    FELabel *adress = [[FELabel alloc] initWithFrame:CGRectMake(10, 310, 80, 20)];
    adress.text = FEString(@"ADRESS");
    [self.view addSubview:adress];
    
    _address = [[UITextField alloc] initWithFrame:CGRectMake(110, 310, 190, 30)];
    _address.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:_address];
    
    //删除按钮
    UIButton *del = [UIButton buttonWithType:UIButtonTypeCustom];
    del.frame = CGRectMake(20, 360, 80, 40);
    [del setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [del setTitle:FEString(@"DELET") forState:UIControlStateNormal];
    [self.view addSubview:del];
    
    //提交按钮
    UIButton *submit = [UIButton buttonWithType:UIButtonTypeCustom];
    submit.frame = CGRectMake(220, 360, 80, 40);
    [submit setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [submit setTitle:FEString(@"SUBMIT") forState:UIControlStateNormal];
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
