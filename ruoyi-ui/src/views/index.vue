<template>
	<div class="page-overview">
		<el-row>
			<el-col :span="24">
				<el-card class="overview-hero" shadow="never">
					<h1 class="title">电影综合管理</h1>
					<!-- <p class="description">本页面仅为静态展示，不包含任何影片列表、排片、统计或接口调用。</p> -->
				</el-card>
			</el-col>
		</el-row>

		<el-row style="margin-top:16px;">
			<el-col :span="24">
				<el-card>
					<h3>小组信息</h3>
					<div class="group">
						<div class="group-name">小组成员：</div>
						<el-row :gutter="12" style="margin-top:8px;">
							<el-col :span="6" v-for="member in members" :key="member">
								<div class="member">{{ member }}</div>
							</el-col>
						</el-row>
					</div>
				</el-card>
			</el-col>
		</el-row>

		<el-footer class="page-footer">电影综合管理</el-footer>
	</div>
</template>

<script>
export default {
	name: 'IndexView',
	data() {
		return {
			members: ['马明月', '陈子安', '刘小雪', '许可心']
		}
	}
}
</script>

<style scoped>
.page-overview { padding: 20px }
.overview-hero { text-align: left }
.title { margin: 0; font-size: 26px; color: #333 }
.description { margin-top: 6px; color: #666 }
.group-name { font-weight: 600; color: #555 }
.member { padding: 10px; background: #fafafa; border-radius: 4px; text-align:center }
.page-footer { text-align: center; margin-top: 20px; color: #999 }
</style>

