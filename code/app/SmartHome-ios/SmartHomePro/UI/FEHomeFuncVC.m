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
      @[@{PNG_KEY:@"menu_alarm",ITEM_TITLE:kString(@"告警信息"),ITEM_ACTION:[self getIvocationWith:@selector(toWarring)]},
        @{PNG_KEY:@"menu_control",ITEM_TITLE:kString(@"设备状态"),ITEM_ACTION:[self getIvocationWith:@selector(toDeviceStatus)]},
        @{PNG_KEY:@"menu_area",ITEM_TITLE:kString(@"日常巡检"),ITEM_ACTION:[self getIvocationWith:@selector(toRoute)]},
        @{PNG_KEY:@"menu_account",ITEM_TITLE:kString(@"基本信息"),ITEM_ACTION:[self getIvocationWith:@selector(toInfo)]},
        @{PNG_KEY:@"menu_plane",ITEM_TITLE:kString(@"智慧管理"),ITEM_ACTION:[self getIvocationWith:@selector(toManage)]}],
      
      @[@{PNG_KEY:@"menu_note",ITEM_TITLE:kString(@"消防常识"),ITEM_ACTION:[self getIvocationWith:@selector(toKnowledge)]},
        @{PNG_KEY:@"menu_mall",ITEM_TITLE:kString(@"设备商城"),ITEM_ACTION:[self getIvocationWith:@selector(toStore)]}]
      ];
    self.headerArray = @[kString(@"智慧消防"),kString(@"其他信息")];
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

-(void)toWarring{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:self];
}

-(void)toDeviceStatus{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:self];
}

-(void)toRoute{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:self];
}

-(void)toInfo{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:self];
}

-(void)toManage{
    [self performSegueWithIdentifier:@"toCompanySegue" sender:self];
}

-(void)toKnowledge{
    [self performSegueWithIdentifier:@"toFireKnowledgeSegue" sender:self];
}

-(void)toStore{
    [self performSegueWithIdentifier:@"toStoreSegue" sender:self];
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
