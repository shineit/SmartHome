//
//  FECommonViewController.m
//  SmartHome
//
//  Created by Seven on 14-10-17.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECommonViewController.h"
#import "FECommonDefine.h"
#import "UIColor+Theme.h"

typedef NS_ENUM(NSInteger, FENavItemDirection) {
    FENavItemLeftDirection,
    FENavItemRightDirection,
};

@interface FECommonViewController ()

@end

@implementation FECommonViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    UIBarButtonItem *backButton = [[UIBarButtonItem alloc] initWithTitle:kString(@"返回") style:UIBarButtonItemStylePlain target:self action:@selector(backPress:)];
    self.navigationItem.backBarButtonItem = backButton;
    
    [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent];
    [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleLightContent animated:NO];
    self.view.backgroundColor = [UIColor ThemeViewBackColor];
}

-(void)backPress:(id)sender{
    [self.navigationController popViewControllerAnimated:YES];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardWillShowNotification object:nil];
	[[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillHide:) name:UIKeyboardWillHideNotification object:nil];
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardWillShowNotification object:nil];
	[[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardWillHideNotification object:nil];
}

- (void)keyboardWillShow:(NSNotification *)notification
{
	NSValue *value = [notification userInfo][UIKeyboardFrameEndUserInfoKey];
	CGRect frame = [self.view convertRect:[value CGRectValue] fromView:nil];
	CGFloat duration = [[notification userInfo][UIKeyboardAnimationDurationUserInfoKey] floatValue];
	[self keyboardWillShow:frame duration:duration];
}

- (void)keyboardWillHide:(NSNotification *)notification
{
	NSValue *value = [notification userInfo][UIKeyboardFrameEndUserInfoKey];
	CGRect frame = [self.view convertRect:[value CGRectValue] fromView:nil];
	CGFloat duration = [[notification userInfo][UIKeyboardAnimationDurationUserInfoKey] floatValue];
	[self keyboardWillHide:frame duration:duration];
}

- (void)keyboardWillShow:(CGRect)newRect duration:(NSTimeInterval)duration
{
}

- (void)keyboardWillHide:(CGRect)newRect duration:(NSTimeInterval)duration
{
    
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)loadRightCustomButtonItemWithTitle:(NSString *)title image:(UIImage *)image{
    [self loadNavItemWithTitle:title image:image target:self action:@selector(rightbarpressed:) direction:FENavItemRightDirection];
}

- (void)loadBackButtonItem{
    [self loadNavItemWithTitle:@"返回" image:[UIImage imageNamed:@"img_back"] target:self action:@selector(backpressed:) direction:FENavItemLeftDirection];
}

- (UIButton *)loadNavItemWithTitle:(NSString *)title image:(UIImage *)bimage target:(id)target action:(SEL)action direction:(FENavItemDirection)direction {
    UIButton *bt = [UIButton buttonWithType:UIButtonTypeCustom];
    [bt setTitle:title forState:UIControlStateNormal];
    bt.frame = CGRectMake(0, 0, 50, 30);
    [bt setBackgroundImage:bimage forState:UIControlStateNormal];
    [bt addTarget:target action:action forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *item = [[UIBarButtonItem alloc] initWithCustomView:bt];

    if (direction==FENavItemLeftDirection) {
        self.navigationItem.leftBarButtonItem = item;
    }else {
        self.navigationItem.rightBarButtonItem = item;
    }
    return bt;
}

-(void)rightbarpressed:(UIButton *)button{
    
}

-(void)backpressed:(UIButton *)button{
    [self.navigationController popViewControllerAnimated:YES];
}

//视图偏移
-(void)screenOffset:(CGFloat)offset{
    [UIView animateWithDuration:0.3 animations:^(void){
        self.view.frame = CGRectMake(0, offset, self.view.frame.size.width, self.view.frame.size.height);
    }];
    
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
