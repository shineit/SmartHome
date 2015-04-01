//
//  FEHomeVC.m
//  SmartHome
//
//  Created by Seven on 15-4-1.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FEHomeFuncVC.h"
#import "FECommonDefine.h"

#define PNG_KEY @"png"
#define ITEM_TITLE   @"title"
#define ITEM_ACTION     @"action"

@interface FEHomeFuncVC ()<UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>

@property (nonatomic, strong) NSArray *datasource;
@property (nonatomic, strong) NSArray *headerArray;
@property (strong, nonatomic) IBOutlet UICollectionView *funcCollectionView;

@end

@implementation FEHomeFuncVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = kString(@"HOME");
    
    
    
    self.datasource =
    @[
      @[@{PNG_KEY:@"home_safe",ITEM_TITLE:kString(@"云安"),ITEM_ACTION:[self getIvocationWith:@selector(tosafe)]},
        @{PNG_KEY:@"home_controll",ITEM_TITLE:kString(@"云控"),ITEM_ACTION:[self getIvocationWith:@selector(tocontroll)]},
        @{PNG_KEY:@"home_camera",ITEM_TITLE:kString(@"云视"),ITEM_ACTION:[self getIvocationWith:@selector(tocamera)]},
        @{PNG_KEY:@"home_warring",ITEM_TITLE:kString(@"告警信息"),ITEM_ACTION:[self getIvocationWith:@selector(towarring)]},
        @{PNG_KEY:@"home_plan",ITEM_TITLE:kString(@"平面图查看"),ITEM_ACTION:[self getIvocationWith:@selector(toscan)]}],
      
      @[@{PNG_KEY:@"home_concentrator",ITEM_TITLE:kString(@"集中器设置"),ITEM_ACTION:[self getIvocationWith:@selector(toconcentrator)]},
        @{PNG_KEY:@"home_region",ITEM_TITLE:kString(@"我的片区"),ITEM_ACTION:[self getIvocationWith:@selector(tomyregion)]}],
      
      @[@{PNG_KEY:@"home_profile",ITEM_TITLE:kString(@"账户设置"),ITEM_ACTION:[self getIvocationWith:@selector(toprofile)]},
        @{PNG_KEY:@"home_service",ITEM_TITLE:kString(@"申请管理"),ITEM_ACTION:[self getIvocationWith:@selector(toservice)]},
        @{PNG_KEY:@"home_news",ITEM_TITLE:kString(@"新闻公告"),ITEM_ACTION:[self getIvocationWith:@selector(tonews)]}]
      ];
    self.headerArray = @[kString(@"云安防"),kString(@"设备管理"),kString(@"个人中心")];
}

#pragma mark - UICollectionViewDataSource

// The cell that is returned must be retrieved from a call to -dequeueReusableCellWithReuseIdentifier:forIndexPath:
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    
    UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"functionCell" forIndexPath:indexPath];
    UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];
    imageView.image = [UIImage imageNamed:self.datasource[indexPath.section][indexPath.row][PNG_KEY]];
    UILabel *label = (UILabel *)[cell viewWithTag:2];
    label.text = self.datasource[indexPath.section][indexPath.row][ITEM_TITLE];
    return cell;
}

-(NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView
{
    return self.datasource.count;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return [self.datasource[section] count];
}

#pragma mark - UICollectionViewDelegateFlowLayout


- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout referenceSizeForHeaderInSection:(NSInteger)section{
    return CGSizeMake(self.view.bounds.size.width, 40);
}

-(UICollectionReusableView *)collectionView:(UICollectionView *)collectionView viewForSupplementaryElementOfKind:(NSString *)kind atIndexPath:(NSIndexPath *)indexPath{
    UICollectionReusableView * reusableview = nil ;
    
    if ( kind == UICollectionElementKindSectionHeader ) {
        UICollectionReusableView *view = [collectionView dequeueReusableSupplementaryViewOfKind:UICollectionElementKindSectionHeader withReuseIdentifier:@"headerView" forIndexPath:indexPath];
        UILabel *tLabel = (UILabel *)[view viewWithTag:1];
        tLabel.text = self.headerArray[indexPath.section];;
        return view;
    }
    return reusableview;
}

#pragma mark - UICollectionViewDelegate
-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    NSInvocation *iv = self.datasource[indexPath.section][indexPath.row][ITEM_ACTION];
    [iv invoke];
}


-(void)tosafe{
    
}

-(void)tocontroll{

}

-(void)tocamera{
    
}

-(void)towarring{
    
}

-(void)toscan{
    
}

-(void)toconcentrator{
    
}

-(void)tomyregion{
    
}

-(void)toprofile{
}

-(void)toservice{
   
}

-(void)tonews{
    
}

-(NSInvocation *)getIvocationWith:(SEL)selector{
    NSMethodSignature *sig=[self methodSignatureForSelector:selector];
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:sig];
    [invocation setSelector:selector];
    [invocation setTarget:self];
    return invocation;
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
