//
//  FECommonTabBarController.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECommonTabBarController.h"
#import <ZBUtilities/UIImage+LogN.h>
#import "UIColor+Theme.h"
#import "UIImage+Resize.h"

@interface FECommonTabBarController ()<UITabBarControllerDelegate>

@end

@implementation FECommonTabBarController

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
    self.tabBar.backgroundImage = [UIImage imageFromColor:[UIColor colorWithRed:233.0f / 255.0f green:234.0f / 255.0f blue:237.0f / 255.0f alpha:1.0f]];
    
    self.tabBar.selectionIndicatorImage = [[UIImage imageFromColor:[UIColor ThemeColor]] imageScaledToSize:CGSizeMake(self.view.bounds.size.width / 4.0f, 49)];
    [[UITabBarItem appearance] setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:
                                                       [UIFont systemFontOfSize:10], NSFontAttributeName,
                                                       [UIColor colorWithRed:51.0f / 255.0f green:51.0f / 255.0f blue:51.0f / 255.0f alpha:1.0f],NSForegroundColorAttributeName,nil] forState:UIControlStateNormal];
    [[UITabBarItem appearance] setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:
                                                       [UIColor whiteColor],NSForegroundColorAttributeName,nil] forState:UIControlStateSelected];
    
    self.tabBar.selectedImageTintColor = [UIColor whiteColor];
}

-(UIImage*)imageWithImageSimple:(UIImage *)image scaledToSize:(CGSize)newSize{
    //创建一个图片容器
    UIGraphicsBeginImageContext(newSize);
    //将原始图片绘制到当前图片容器中
    [image drawInRect:CGRectMake(0, 0, newSize.width, newSize.height)];
    UIImage *newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return newImage;
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
